package ericsson.project.mnrao.services;

import ericsson.project.mnrao.models.Node;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OptimizerModuleTest {

    private static final int NODE_ID = 1;
    private static final double BANDWIDTH_USAGE = 100.0;
    private static final double BANDWIDTH_ALLOCATED = 200.0;
    private static final double MEMORY_USAGE = 50.0;
    private static final double MEMORY_ALLOCATED = 100.0;
    private static final double CPU_USAGE = 50.0;
    private static final double CPU_ALLOCATED = 100.0;
    
    @Mock
    private RUDataSimulator ruDataSimulator;

    @InjectMocks
    private OptimizerModule optimizerModule;

    private Node[] nodesArray;

    @BeforeEach
    public void setUp() throws NoSuchFieldException, IllegalAccessException {
        MockitoAnnotations.initMocks(this);
        optimizerModule = new OptimizerModule();
        optimizerModule.ruDataSimulator = ruDataSimulator;

        nodesArray = new Node[5];
        for (int i = 0; i < 5; i++) {
            nodesArray[i] = new Node();
            nodesArray[i].setNodeId(i + 1);
        }

        setRUDataSimulatorNodesField(nodesArray);
    }

    private void setRUDataSimulatorNodesField(Node[] nodes) throws NoSuchFieldException, IllegalAccessException {
        Field nodesField = RUDataSimulator.class.getDeclaredField("nodes");
        nodesField.setAccessible(true);
        nodesField.set(ruDataSimulator, nodes);
    }

    private Node createTestNode(double usage, double allocated, NodeType nodeType) {
        Node node = new Node();
        node.setNodeId(NODE_ID);
        switch (nodeType) {
            case BANDWIDTH:
                node.setBandwidthUsage(usage);
                node.setBandwidthAllocated(allocated);
                break;
            case MEMORY:
                node.setMemoryUsage(usage);
                node.setMemoryAllocated(allocated);
                break;
            case CPU:
                node.setCpuUsage(usage);
                node.setCpuAllocated(allocated);
                break;
        }
        return node;
    }

    private void assertNodeAllocation(double expected, NodeType nodeType) throws NoSuchFieldException, IllegalAccessException {
        Field nodesField = RUDataSimulator.class.getDeclaredField("nodes");
        nodesField.setAccessible(true);
        Node[] nodesArray = (Node[]) nodesField.get(ruDataSimulator);
        switch (nodeType) {
            case BANDWIDTH:
                assertEquals(expected, nodesArray[0].getBandwidthAllocated());
                break;
            case MEMORY:
                assertEquals(expected, nodesArray[0].getMemoryAllocated());
                break;
            case CPU:
                assertEquals(expected, nodesArray[0].getCpuAllocated());
                break;
        }
    }

    @Test
    public void testIncreaseBandwidthAllocation() throws NoSuchFieldException, IllegalAccessException {
        Node node = createTestNode(BANDWIDTH_USAGE, BANDWIDTH_ALLOCATED, NodeType.BANDWIDTH);
        optimizerModule.increaseBandwidthAllocation(node);
        assertNodeAllocation(125.00, NodeType.BANDWIDTH);
    }

    @Test
    public void testDecreaseBandwidthAllocation() throws NoSuchFieldException, IllegalAccessException {
        Node node = createTestNode(BANDWIDTH_USAGE, BANDWIDTH_ALLOCATED, NodeType.BANDWIDTH);
        optimizerModule.decreaseBandwidthAllocation(node);
        assertNodeAllocation(110.00, NodeType.BANDWIDTH);
    }

    @Test
    public void testIncreaseMemoryAllocation() throws NoSuchFieldException, IllegalAccessException {
        Node node = createTestNode(MEMORY_USAGE, MEMORY_ALLOCATED, NodeType.MEMORY);
        optimizerModule.increaseMemoryAllocation(node);
        assertNodeAllocation(75.00, NodeType.MEMORY);
    }

    @Test
    public void testDecreaseMemoryAllocation() throws NoSuchFieldException, IllegalAccessException {
        Node node = createTestNode(MEMORY_USAGE, MEMORY_ALLOCATED, NodeType.MEMORY);
        optimizerModule.decreaseMemoryAllocation(node);
        assertNodeAllocation(60.00, NodeType.MEMORY);
    }

    @Test
    public void testIncreaseCPUAllocation() throws NoSuchFieldException, IllegalAccessException {
        Node node = createTestNode(CPU_USAGE, CPU_ALLOCATED, NodeType.CPU);
        optimizerModule.increaseCPUAllocation(node);
        assertNodeAllocation(75.00, NodeType.CPU);
    }

    @Test
    public void testDecreaseCPUAllocation() throws NoSuchFieldException, IllegalAccessException {
        Node node = createTestNode(CPU_USAGE, CPU_ALLOCATED, NodeType.CPU);
        optimizerModule.decreaseCPUAllocation(node);
        assertNodeAllocation(60.00, NodeType.CPU);
    }

    @Test
    public void testAnalyseNodeBandwidth() throws NoSuchFieldException, IllegalAccessException {
        Node node = createTestNode(190.0, BANDWIDTH_ALLOCATED, NodeType.BANDWIDTH);
        optimizerModule.analyseNodeBandwidth(node);
        optimizerModule.analyseNodeBandwidth(node); // Simulate reaching the threshold for increase
        optimizerModule.analyseNodeBandwidth(node); // Simulate reaching the threshold for increase
        assertNodeAllocation(215.00, NodeType.BANDWIDTH);
    }

    @Test
    public void testAnalyseNodeMemory() throws NoSuchFieldException, IllegalAccessException {
        Node node = createTestNode(90.0, MEMORY_ALLOCATED, NodeType.MEMORY);
        optimizerModule.analyseNodeMemory(node);
        optimizerModule.analyseNodeMemory(node); // Simulate reaching the threshold for increase
        optimizerModule.analyseNodeMemory(node); // Simulate reaching the threshold for increase
        assertNodeAllocation(115.00, NodeType.MEMORY);
    }

    @Test
    public void testAnalyseNodeCPU() throws NoSuchFieldException, IllegalAccessException {
        Node node = createTestNode(90.0, CPU_ALLOCATED, NodeType.CPU);
        optimizerModule.analyseNodeCPU(node);
        optimizerModule.analyseNodeCPU(node); // Simulate reaching the threshold for increase
        optimizerModule.analyseNodeCPU(node); // Simulate reaching the threshold for increase
        assertNodeAllocation(115.00, NodeType.CPU);
    }
    @Test
    public void testAnalyseNodeBandwidthIncrease() throws NoSuchFieldException, IllegalAccessException {
        Node node = createTestNode(190.0, BANDWIDTH_ALLOCATED, NodeType.BANDWIDTH);
        optimizerModule.analyseNodeBandwidth(node);
        optimizerModule.analyseNodeBandwidth(node); // Simulate reaching the threshold for increase
        optimizerModule.analyseNodeBandwidth(node); // Simulate reaching the threshold for increase
        assertNodeAllocation(215.00, NodeType.BANDWIDTH);
    }

    @Test
    public void testAnalyseNodeBandwidthDecrease() throws NoSuchFieldException, IllegalAccessException {
        Node node = createTestNode(50.0, BANDWIDTH_ALLOCATED, NodeType.BANDWIDTH);
        optimizerModule.analyseNodeBandwidth(node);
        optimizerModule.analyseNodeBandwidth(node); // Simulate reaching the threshold for decrease
        optimizerModule.analyseNodeBandwidth(node); // Simulate reaching the threshold for decrease
        assertNodeAllocation(60.00, NodeType.BANDWIDTH);
    }

    @Test
    public void testAnalyseNodeMemoryDecrease() throws NoSuchFieldException, IllegalAccessException {
        Node node = createTestNode(20.0, MEMORY_ALLOCATED, NodeType.MEMORY);
        optimizerModule.analyseNodeMemory(node);
        optimizerModule.analyseNodeMemory(node); // Simulate reaching the threshold for decrease
        optimizerModule.analyseNodeMemory(node); // Simulate reaching the threshold for decrease
        assertNodeAllocation(30.00, NodeType.MEMORY);
    }

    @Test
    public void testAnalyseNodeCPUIncrease() throws NoSuchFieldException, IllegalAccessException {
        Node node = createTestNode(90.0, CPU_ALLOCATED, NodeType.CPU);
        optimizerModule.analyseNodeCPU(node);
        optimizerModule.analyseNodeCPU(node); // Simulate reaching the threshold for increase
        optimizerModule.analyseNodeCPU(node); // Simulate reaching the threshold for increase
        assertNodeAllocation(115.00, NodeType.CPU);
    }

    @Test
    public void testAnalyseNodeCPUDecrease() throws NoSuchFieldException, IllegalAccessException {
        Node node = createTestNode(20.0, CPU_ALLOCATED, NodeType.CPU);
        optimizerModule.analyseNodeCPU(node);
        optimizerModule.analyseNodeCPU(node); // Simulate reaching the threshold for decrease
        optimizerModule.analyseNodeCPU(node); // Simulate reaching the threshold for decrease
        assertNodeAllocation(30.00, NodeType.CPU);
    }
    @Test
    public void testAnalyseNodeMemoryIncrease() throws NoSuchFieldException, IllegalAccessException {
    	Node node = createTestNode(90.0, MEMORY_ALLOCATED, NodeType.MEMORY);
    	optimizerModule.analyseNodeMemory(node);
    	optimizerModule.analyseNodeMemory(node); // Simulate reaching the threshold for increase
    	optimizerModule.analyseNodeMemory(node); // Simulate reaching the threshold for increase
    	assertNodeAllocation(115.00, NodeType.MEMORY);
    }

    private enum NodeType {
        BANDWIDTH, MEMORY, CPU
    }

}
