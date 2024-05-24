package ericsson.project.mnrao.models;

import java.time.LocalDateTime;

public class Node {

    private int nodeId;
    private int networkId;
    private double cpuUsage;
    private double memoryUsage;
    private double bandwithUsage;
    private double cpuAllocated;
    private double memoryAllocated;
    private double bandwithAllocated;
    private LocalDateTime timestamp;

    public Node(int nodeId, int networkId) {
        this.nodeId = nodeId;
        this.networkId = networkId;
    }

    public Node() {
    }

    public int getNodeId() {
        return nodeId;
    }

    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }

    public int getNetworkId() {
        return networkId;
    }

    public void setNetworkId(int networkId) {
        this.networkId = networkId;
    }

    public double getCpuUsage() {
        return cpuUsage;
    }

    public void setCpuUsage(double cpuUsage) {
        this.cpuUsage = cpuUsage;
    }

    public double getMemoryUsage() {
        return memoryUsage;
    }

    public void setMemoryUsage(double memoryUsage) {
        this.memoryUsage = memoryUsage;
    }

    public double getCpuAllocated() {
        return cpuAllocated;
    }

    public void setCpuAllocated(double cpuAllocated) {
        this.cpuAllocated = cpuAllocated;
    }

    public double getBandwithUsage() {
        return bandwithUsage;
    }

    public void setBandwithUsage(double bandwithUsage) {
        this.bandwithUsage = bandwithUsage;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public double getBandwithAllocated() {
        return bandwithAllocated;
    }

    public void setBandwithAllocated(double bandwithAllocated) {
        this.bandwithAllocated = bandwithAllocated;
    }

    public double getMemoryAllocated() {
        return memoryAllocated;
    }

    public void setMemoryAllocated(double memoryAllocated) {
        this.memoryAllocated = memoryAllocated;
    }

    @Override
    public String toString() {
        return "Node{" +
                "nodeId=" + nodeId +
                ", networkId=" + networkId +
                ", cpuUsage=" + cpuUsage +
                ", memoryUsage=" + memoryUsage +
                ", bandwithUsage=" + bandwithUsage +
                ", cpuAllocated=" + cpuAllocated +
                ", memoryAllocated=" + memoryAllocated +
                ", bandwithAllocated=" + bandwithAllocated +
                ", timestamp=" + timestamp +
                '}';
    }
}
