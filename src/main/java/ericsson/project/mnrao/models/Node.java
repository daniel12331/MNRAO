package ericsson.project.mnrao.models;

import java.time.LocalDateTime;

public class Node {

    private int nodeId;
    private int networkId;
    private double cpuUsage;
    private double memoryUsage;
    private double bandwidthUsage;
    private double cpuAllocated;
    private double memoryAllocated;
    private double bandwidthAllocated;
    private LocalDateTime timestamp;

    public Node(int nodeId, int networkId, double cpuUsage, double memoryUsage, double bandwidthUsage, double cpuAllocated, double memoryAllocated, double bandwidthAllocated) {
        this.nodeId = nodeId;
        this.networkId = networkId;
        this.cpuUsage = cpuUsage;
        this.memoryUsage = memoryUsage;
        this.bandwidthUsage = bandwidthUsage;
        this.cpuAllocated = cpuAllocated;
        this.memoryAllocated = memoryAllocated;
        this.bandwidthAllocated = bandwidthAllocated;
        this.timestamp = LocalDateTime.now();
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

    public double getBandwidthUsage() {
        return bandwidthUsage;
    }

    public void setBandwidthUsage(double bandwidthUsage) {
        this.bandwidthUsage = bandwidthUsage;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public double getBandwidthAllocated() {
        return bandwidthAllocated;
    }

    public void setBandwidthAllocated(double bandwidthAllocated) {
        this.bandwidthAllocated = bandwidthAllocated;
    }

    public double getMemoryAllocated() {
        return memoryAllocated;
    }

    public void setMemoryAllocated(double memoryAllocated) {
        this.memoryAllocated = memoryAllocated;
    }

    @Override
    public String toString() {
        setTimestamp(LocalDateTime.now());
        return "Node{" +
                "nodeId=" + nodeId +
                ", networkId=" + networkId +
                ", cpuUsage=" + cpuUsage +
                ", memoryUsage=" + memoryUsage +
                ", bandwidthUsage=" + bandwidthUsage +
                ", cpuAllocated=" + cpuAllocated +
                ", memoryAllocated=" + memoryAllocated +
                ", bandwidthAllocated=" + bandwidthAllocated +
                ", timestamp=" + timestamp +
                '}';
    }
}