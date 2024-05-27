package ericsson.project.mnrao.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class NodeTest {

    private static final int DEFAULT_NODE_ID = 1;
    private static final int DEFAULT_NETWORK_ID = 101;
    private static final double DEFAULT_CPU_USAGE = 20.5;
    private static final double DEFAULT_MEMORY_USAGE = 30.5;
    private static final double DEFAULT_BANDWIDTH_USAGE = 40.5;
    private static final double DEFAULT_CPU_ALLOCATED = 50.0;
    private static final double DEFAULT_MEMORY_ALLOCATED = 60.0;
    private static final double DEFAULT_BANDWIDTH_ALLOCATED = 70.0;
    private static final LocalDateTime DEFAULT_TIMESTAMP = LocalDateTime.of(2023, 1, 1, 12, 0, 0);

    private static final int ANOTHER_NODE_ID = 2;
    private static final int ANOTHER_NETWORK_ID = 202;
    private static final double ANOTHER_CPU_USAGE = 25.5;
    private static final double ANOTHER_MEMORY_USAGE = 35.5;
    private static final double ANOTHER_BANDWIDTH_USAGE = 45.5;
    private static final double ANOTHER_MEMORY_ALLOCATED = 65.0;
    private static final double ANOTHER_BANDWIDTH_ALLOCATED = 75.0;

    private static final int UPDATED_NODE_ID = 10;
    private static final int UPDATED_NETWORK_ID = 110;
    private static final double UPDATED_CPU_USAGE = 21.5;
    private static final double UPDATED_MEMORY_USAGE = 31.5;
    private static final double UPDATED_BANDWIDTH_USAGE = 41.5;
    private static final double UPDATED_CPU_ALLOCATED = 51.0;
    private static final double UPDATED_MEMORY_ALLOCATED = 61.0;
    private static final double UPDATED_BANDWIDTH_ALLOCATED = 71.0;

    private Node node;

    @BeforeEach
    void setUp() {
        node = new Node(DEFAULT_NODE_ID, DEFAULT_NETWORK_ID, DEFAULT_CPU_USAGE, DEFAULT_MEMORY_USAGE, DEFAULT_BANDWIDTH_USAGE, DEFAULT_CPU_ALLOCATED, DEFAULT_MEMORY_ALLOCATED, DEFAULT_BANDWIDTH_ALLOCATED);
    }

    @Test
    void testDefaultConstructor() {
        Node defaultNode = new Node();
        assertNotNull(defaultNode);
    }

    @Test
    void testParameterizedConstructor() {
        assertEquals(DEFAULT_NODE_ID, node.getNodeId());
        assertEquals(DEFAULT_NETWORK_ID, node.getNetworkId());
        assertEquals(DEFAULT_CPU_USAGE, node.getCpuUsage());
        assertEquals(DEFAULT_MEMORY_USAGE, node.getMemoryUsage());
        assertEquals(DEFAULT_BANDWIDTH_USAGE, node.getBandwidthUsage());
        assertEquals(DEFAULT_CPU_ALLOCATED, node.getCpuAllocated());
        assertEquals(DEFAULT_MEMORY_ALLOCATED, node.getMemoryAllocated());
        assertEquals(DEFAULT_BANDWIDTH_ALLOCATED, node.getBandwidthAllocated());
        assertNotNull(node.getTimestamp());
    }

    @Test
    void testParameterizedConstructorWithoutCpuAllocated() {
        Node anotherNode = new Node(ANOTHER_NODE_ID, ANOTHER_NETWORK_ID, ANOTHER_CPU_USAGE, ANOTHER_MEMORY_USAGE, ANOTHER_BANDWIDTH_USAGE, ANOTHER_MEMORY_ALLOCATED, ANOTHER_BANDWIDTH_ALLOCATED);
        assertEquals(ANOTHER_NODE_ID, anotherNode.getNodeId());
        assertEquals(ANOTHER_NETWORK_ID, anotherNode.getNetworkId());
        assertEquals(ANOTHER_CPU_USAGE, anotherNode.getCpuUsage());
        assertEquals(ANOTHER_MEMORY_USAGE, anotherNode.getMemoryUsage());
        assertEquals(ANOTHER_BANDWIDTH_USAGE, anotherNode.getBandwidthUsage());
        assertEquals(ANOTHER_MEMORY_ALLOCATED, anotherNode.getMemoryAllocated());
        assertEquals(ANOTHER_BANDWIDTH_ALLOCATED, anotherNode.getBandwidthAllocated());
        assertNotNull(anotherNode.getTimestamp());
    }

    @Test
    void testSettersAndGetters() {
        node.setNodeId(UPDATED_NODE_ID);
        assertEquals(UPDATED_NODE_ID, node.getNodeId());

        node.setNetworkId(UPDATED_NETWORK_ID);
        assertEquals(UPDATED_NETWORK_ID, node.getNetworkId());

        node.setCpuUsage(UPDATED_CPU_USAGE);
        assertEquals(UPDATED_CPU_USAGE, node.getCpuUsage());

        node.setMemoryUsage(UPDATED_MEMORY_USAGE);
        assertEquals(UPDATED_MEMORY_USAGE, node.getMemoryUsage());

        node.setBandwidthUsage(UPDATED_BANDWIDTH_USAGE);
        assertEquals(UPDATED_BANDWIDTH_USAGE, node.getBandwidthUsage());

        node.setCpuAllocated(UPDATED_CPU_ALLOCATED);
        assertEquals(UPDATED_CPU_ALLOCATED, node.getCpuAllocated());

        node.setMemoryAllocated(UPDATED_MEMORY_ALLOCATED);
        assertEquals(UPDATED_MEMORY_ALLOCATED, node.getMemoryAllocated());

        node.setBandwidthAllocated(UPDATED_BANDWIDTH_ALLOCATED);
        assertEquals(UPDATED_BANDWIDTH_ALLOCATED, node.getBandwidthAllocated());

        LocalDateTime now = LocalDateTime.now();
        node.setTimestamp(now);
        assertEquals(now, node.getTimestamp());
    }

}