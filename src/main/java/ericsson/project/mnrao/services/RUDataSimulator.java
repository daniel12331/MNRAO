package ericsson.project.mnrao.services;

import ericsson.project.mnrao.models.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Slf4j
@Service
public class RUDataSimulator {

    @Autowired
    KafkaTemplate<String, Node> kafkaTemplate;

    public final Random random = new Random();
    public final Node[] nodes = {
            new Node(1, 101, 10.0, 10.0, 10.0, 50.0, 50.0, 50.0),
            new Node(2, 102, 10.0, 10.0, 10.0, 50.0, 50.0, 50.0),
            new Node(3, 103, 10.0, 10.0, 10.0, 50.0, 50.0, 50.0),
            new Node(4, 104, 10.0, 10.0, 10.0, 50.0, 50.0, 50.0),
            new Node(5, 105, 10.0, 10.0, 10.0, 50.0, 50.0, 50.0)
    };
    private int currentNodeIndex = 0;

    public Node[] getNodes() {
        return nodes;
    }

    // "freak mode" - can choose a node and an individual resource
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

    @Scheduled(fixedDelay = 2500)
    public void scheduleNodeData() {
        processNodeData();
    }

    public void processNodeData() {
        Node currentNode = nodes[currentNodeIndex];
        simulateNodeData(currentNode);
        currentNodeIndex = (currentNodeIndex + 1) % nodes.length;
    }

    public void simulateNodeData(Node node) {
        node.setCpuUsage(Math.round(Math.min(generateUsage(node.getCpuUsage(), node.getCpuAllocated()), node.getCpuAllocated())));
        node.setMemoryUsage(Math.round(Math.min(generateUsage(node.getMemoryUsage(), node.getMemoryAllocated()), node.getMemoryAllocated())));
        node.setBandwidthUsage(Math.round(Math.min(generateUsage(node.getBandwidthUsage(), node.getBandwidthAllocated()), node.getBandwidthAllocated())));

        kafkaTemplate.send("node-topic", "taskId", node);
        log.info(String.valueOf(node));
    }

    private double generateUsage(double lastUsage, double allocated) {
        double variation = 5.0;
        double newUsage = lastUsage + (random.nextDouble() * 2 * variation - variation);
        return Math.max(0, Math.min(newUsage, allocated));
    }
}
