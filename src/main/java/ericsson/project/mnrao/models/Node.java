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

    public Node(int nodeId, int networkId, double cpuUsage, double memoryUsage, double bandwithUsage, double cpuAllocated, double memoryAllocated, double bandwithAllocated, LocalDateTime timestamp) {
        this.nodeId = nodeId;
        this.networkId = networkId;
        this.cpuUsage = cpuUsage;
        this.memoryUsage = memoryUsage;
        this.bandwithUsage = bandwithUsage;
        this.cpuAllocated = cpuAllocated;
        this.memoryAllocated = memoryAllocated;
        this.bandwithAllocated = bandwithAllocated;
        this.timestamp = timestamp;
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
}
