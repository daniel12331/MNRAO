package ericsson.project.mnrao.services;

import ericsson.project.mnrao.models.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class OptimizerModule {
    final double THRESHOLD = 15.00;

    final int CPU_INCREASE_FLAG = 3, CPU_DECREASE_FLAG = 3;
    Map<Integer, Integer> cpuFlagDecrease = new HashMap<Integer, Integer>();
    Map<Integer, Integer> cpuFlagIncrease = new HashMap<Integer, Integer>();

    final int MEMORY_INCREASE_FLAG = 3, MEMORY_DECREASE_FLAG = 3;
    Map<Integer, Integer> memFlagDecrease = new HashMap<Integer, Integer>();
    Map<Integer, Integer> memFlagIncrease = new HashMap<Integer, Integer>();

    final int BAND_INCREASE_FLAG = 3, BAND_DECREASE_FLAG = 3;
    Map<Integer, Integer> bandFlagDecrease = new HashMap<Integer, Integer>();
    Map<Integer, Integer> bandFlagIncrease = new HashMap<Integer, Integer>();

    @Autowired
    RUDataSimulator ruDataSimulator;

//    @Autowired
//    RecommendedMsgRepo recommendedMsgRepo;

    @KafkaListener(topics = {"node-topic"}, groupId = "task-group")
    public void consume(Node node){
        analyseNodeCPU(node);
        analyseNodeMemory(node);
        analyseNodeBandwidth(node);

    }

    // NODE BANDWIDTH ANALYSES //////////////////////////////////////////////////////////////////////////////////////////
    public void analyseNodeBandwidth(Node node) {
        if(node.getBandwidthUsage() + THRESHOLD >= node.getBandwidthAllocated()){
            System.out.println(node.getNodeId() + " Node may need more resources Bandwidth Usage: " + node.getBandwidthUsage() + " Bandwidth Allocated: " + node.getBandwidthAllocated());
            bandFlagIncrease.merge(node.getNodeId(), 1, Integer::sum);

            if(bandFlagIncrease.get(node.getNodeId()) == BAND_INCREASE_FLAG){
                increaseBandwidthAllocation(node);
            }

        } else if (node.getBandwidthUsage() + THRESHOLD <= node.getBandwidthAllocated()) {
            System.out.println(node.getNodeId() + " Node may need less resources Bandwidth Usage: " + node.getBandwidthUsage() + " Bandwidth Allocated: " + node.getBandwidthAllocated());
            bandFlagDecrease.merge(node.getNodeId(), 1, Integer::sum);

            if(bandFlagDecrease.get(node.getNodeId()) == BAND_DECREASE_FLAG){
                decreaseBandwidthAllocation(node);
            }
        }
    }
    public void increaseBandwidthAllocation(Node node) {
        System.out.println("This is the third flag increasing resources");
        System.out.println("Increasing: " + node);
        bandFlagIncrease.put(node.getNodeId(), 0);
        ruDataSimulator.nodes[node.getNodeId() - 1].setBandwidthAllocated(node.getBandwidthUsage() + 25.00);
    }

    public void decreaseBandwidthAllocation(Node node) {
        System.out.println("This is the third flag decreasing resources");
        System.out.println("Reducing: " + node);
        bandFlagDecrease.put(node.getNodeId(), 0);
        ruDataSimulator.nodes[node.getNodeId() - 1].setBandwidthAllocated(node.getBandwidthUsage() + 10.00);
    }


    // NODE MEMORY ANALYSES //////////////////////////////////////////////////////////////////////////////////////////
    public void analyseNodeMemory(Node node) {
        if(node.getMemoryUsage() + THRESHOLD >= node.getMemoryAllocated()){
            System.out.println(node.getNodeId() + " Node may need more resources Memory Usage: " + node.getMemoryUsage() + " Memory Allocated: " + node.getMemoryAllocated());
            memFlagIncrease.merge(node.getNodeId(), 1, Integer::sum);

            if(memFlagIncrease.get(node.getNodeId()) == MEMORY_INCREASE_FLAG){
                increaseMemoryAllocation(node);
            }

        } else if (node.getMemoryUsage() + THRESHOLD <= node.getMemoryAllocated()) {
            System.out.println(node.getNodeId() + " Node may need less resources Memory Usage: " + node.getMemoryUsage() + " Memory Allocated: " + node.getMemoryAllocated());
            memFlagDecrease.merge(node.getNodeId(), 1, Integer::sum);

            if(memFlagDecrease.get(node.getNodeId()) == MEMORY_DECREASE_FLAG){
                decreaseMemoryAllocation(node);
            }
        }
    }
    public void increaseMemoryAllocation(Node node) {
        System.out.println("This is the third flag increasing resources");
        System.out.println("Increasing: " + node);
        memFlagIncrease.put(node.getNodeId(), 0);
        ruDataSimulator.nodes[node.getNodeId() - 1].setMemoryAllocated(node.getMemoryUsage() + 25.00);
    }

    public void decreaseMemoryAllocation(Node node) {
        System.out.println("This is the third flag decreasing resources");
        System.out.println("Reducing: " + node);
        memFlagDecrease.put(node.getNodeId(), 0);
        ruDataSimulator.nodes[node.getNodeId() - 1].setMemoryAllocated(node.getMemoryUsage() + 10.00);
    }

    // NODE CPU ANALYSES //////////////////////////////////////////////////////////////////////////////////////////
    public void analyseNodeCPU(Node node) {
        if(node.getCpuUsage() + THRESHOLD >= node.getCpuAllocated()){
            System.out.println(node.getNodeId() + " Node may need more resources CPU Usage: " + node.getCpuUsage() + " CPU Allocated: " + node.getCpuAllocated());
            cpuFlagIncrease.merge(node.getNodeId(), 1, Integer::sum);

            if(cpuFlagIncrease.get(node.getNodeId()) == CPU_INCREASE_FLAG){
                increaseCPUAllocation(node);
            }

        } else if (node.getCpuUsage() + THRESHOLD <= node.getCpuAllocated()) {
            System.out.println(node.getNodeId() + " Node may need less resources CPU Usage: " + node.getCpuUsage() + " CPU Allocated: " + node.getCpuAllocated());
            cpuFlagDecrease.merge(node.getNodeId(), 1, Integer::sum);
            if(cpuFlagDecrease.get(node.getNodeId()) == CPU_DECREASE_FLAG){
                decreaseCPUAllocation(node);
            }
        }
    }

    public void increaseCPUAllocation(Node node) {
        System.out.println("This is the third flag increasing resources");
        System.out.println("Increasing: " + node);
        cpuFlagIncrease.put(node.getNodeId(), 0);
        ruDataSimulator.nodes[node.getNodeId() - 1].setCpuAllocated(node.getCpuUsage() + 25.00);
    }

    public void decreaseCPUAllocation(Node node) {
        System.out.println("This is the third flag decreasing resources");
        System.out.println("Reducing: " + node);
        cpuFlagDecrease.put(node.getNodeId(), 0);
        ruDataSimulator.nodes[node.getNodeId() - 1].setCpuAllocated(node.getCpuUsage() + 10.00);
//        recommendedMsgRepo.save(new RecommendationMsg());
    }
}
