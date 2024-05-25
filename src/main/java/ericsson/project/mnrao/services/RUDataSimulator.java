package ericsson.project.mnrao.services;

import ericsson.project.mnrao.models.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class RUDataSimulator {

    @Autowired
    KafkaTemplate kafkaTemplate;

    private final Random random = new Random();
    private final Node node1 = new Node(1, 101, 10.0, 10.0, 10.0, 50.0, 50.0, 50.0);
    private final Node node2 = new Node(2, 102, 10.0, 10.0, 10.0, 50.0, 50.0, 50.0);
    private final Node node3 = new Node(3, 103, 10.0, 10.0, 10.0, 50.0, 50.0, 50.0);
    private final Node node4 = new Node(4, 104, 10.0, 10.0, 10.0, 50.0, 50.0, 50.0);
    private final Node node5 = new Node(5, 105, 10.0, 10.0, 10.0, 50.0, 50.0, 50.0);

    public final Node[] nodes = {node1, node2, node3, node4, node5};
    private int currentNodeIndex = 0;

    // "freak mode" - can choose a node and an individual resource - call from front end?
    // will only ever push resource value to max allocated resource
    // im still not sure about stopping it and returning to normal state?
    // can it be called using an endpoint?
    public void generateMaxLoad(Node node, String resource) {
        switch (resource.toLowerCase()) {
            case "cpu":
                node.setCpuUsage(node.getCpuAllocated());
                break;
            case "memory":
                node.setMemoryUsage(node.getMemoryAllocated());
                break;
            case "bandwidth":
                node.setBandwidthUsage(node.getBandwidthAllocated() - 5);
                break;
            default:
                System.out.println("Invalid variable specified. Please specify 'cpu', 'memory', or 'bandwidth'.");
        }
    }

    // this method schedules each node in sequence 1-5 and repeats continuously
    // it also calls simulateNodeData() which generates random data for each resource
    // usage variable cpu, memory, bandwidth
    @Scheduled(fixedDelay = 2500)
    public void scheduleNodeData() {
        while (true) {
            simulateNodeData(nodes[currentNodeIndex]);
            currentNodeIndex = (currentNodeIndex + 1) % nodes.length;
            try {
                Thread.sleep(1500); // control frequency of node data generation here currently 1.5 sec
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void simulateNodeData(Node node) {
        node.setCpuUsage(Math.round(Math.min(generateUsage(node.getCpuUsage(), node.getCpuAllocated()), node.getCpuAllocated())));
        node.setMemoryUsage(Math.round(Math.min(generateUsage(node.getMemoryUsage(), node.getMemoryAllocated()), node.getMemoryAllocated())));
        node.setBandwidthUsage(Math.round(Math.min(generateUsage(node.getBandwidthUsage(), node.getBandwidthAllocated()), node.getBandwidthAllocated())));

        // replace this println with message for kafka template
        kafkaTemplate.send("node-topic", "taskId", node);
    }

    private double generateUsage(double lastUsage, double allocated) {
        double variation = 5.0; // Possibly better set a little higher?
        double newUsage = lastUsage + (random.nextDouble() * 2 * variation - variation);
        return Math.max(0, Math.min(newUsage, allocated));
    }
}