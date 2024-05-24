package ericsson.project.mnrao.services;

import ericsson.project.mnrao.models.Node;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class RUDataSimulator {
    Random random = new Random();
    Node node1 = new Node(1, 101);
    Node node2 = new Node(2, 102);
    Node node3 = new Node(3, 103);
    Node node4 = new Node(4, 104);
    Node node5 = new Node(1, 105);


    public void generateNodeData() {
        while(true) {
            int randomNode = getRandomNode();
            switch (randomNode) {
                case 1:
                    simulateNodeOne();
                case 2:
                    simulateNodeTwo();
                case 3:
                    simulateNodeThree();
                case 4:
                    simulateNodeFour();
                case 5:
                    simulateNodeFive();
            }
            try {
                Thread.sleep(2500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void simulateNodeOne() {
        node1.setCpuAllocated(20.0);
        node1.setMemoryAllocated(20.0);
        node1.setBandwithAllocated(20.0);
        node1.setCpuUsage(Math.round(generateUsage(0, 100)));
        node1.setMemoryUsage(Math.round(generateUsage(0, 100)));
        node1.setBandwithUsage(Math.round(generateUsage(0, 100)));
        System.out.println(node1.toString());
    }

    public void simulateNodeTwo() {
        node2.setCpuAllocated(20.0);
        node2.setMemoryAllocated(20.0);
        node2.setBandwithAllocated(20.0);
        node2.setCpuUsage(Math.round(generateUsage(0, 100)));
        node2.setMemoryUsage(Math.round(generateUsage(0, 100)));
        node2.setBandwithUsage(Math.round(generateUsage(0, 100)));
        System.out.println(node3.toString());
    }

    public void simulateNodeThree() {
        node3.setCpuAllocated(20.0);
        node3.setMemoryAllocated(20.0);
        node3.setBandwithAllocated(20.0);
        node3.setCpuUsage(Math.round(generateUsage(0, 100)));
        node3.setMemoryUsage(Math.round(generateUsage(0, 100)));
        node3.setBandwithUsage(Math.round(generateUsage(0, 100)));
        System.out.println(node3.toString());
    }

    public void simulateNodeFour() {
        node4.setCpuAllocated(20.0);
        node4.setMemoryAllocated(20.0);
        node4.setBandwithAllocated(20.0);
        node4.setCpuUsage(Math.round(generateUsage(0, 100)));
        node4.setMemoryUsage(Math.round(generateUsage(0, 100)));
        node4.setBandwithUsage(Math.round(generateUsage(0, 100)));
        System.out.println(node4.toString());
    }

    public void simulateNodeFive() {
        node5.setCpuAllocated(20.0);
        node5.setMemoryAllocated(20.0);
        node5.setBandwithAllocated(20.0);
        node5.setCpuUsage(Math.round(generateUsage(0, 100)));
        node5.setMemoryUsage(Math.round(generateUsage(0, 100)));
        node5.setBandwithUsage(Math.round(generateUsage(0, 100)));
        System.out.println(node5.toString());
    }

    public int getRandomNode() {
        return random.nextInt(5) + 1;
    }

    private double generateUsage(double min, double max) {
        return min + (max - min) * random.nextDouble();
    }
}