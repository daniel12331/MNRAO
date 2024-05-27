package ericsson.project.mnrao.services;

import ericsson.project.mnrao.models.Node;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.lang.reflect.Field;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OptimizerModuleTest {

    @Mock
    private RUDataSimulator ruDataSimulator;

    private OptimizerModule optimizerModule;

    @BeforeEach
    public void setUp() throws NoSuchFieldException, IllegalAccessException {
        MockitoAnnotations.initMocks(this);
        optimizerModule = new OptimizerModule();
        optimizerModule.ruDataSimulator = ruDataSimulator;

        Field nodesField = RUDataSimulator.class.getDeclaredField("nodes");
        nodesField.setAccessible(true);
        Node[] nodesArray = new Node[5]; // Assuming 5 nodes
        for (int i = 0; i < 5; i++) {
            nodesArray[i] = new Node();
        }
        nodesField.set(ruDataSimulator, nodesArray);
    }

    @Test
    public void testIncreaseBandwidthAllocation() throws NoSuchFieldException, IllegalAccessException {
        Node node = new Node();
        node.setNodeId(1);
        node.setBandwidthUsage(100);
        node.setBandwidthAllocated(200);

        optimizerModule.increaseBandwidthAllocation(node);

        Field nodesField = RUDataSimulator.class.getDeclaredField("nodes");
        nodesField.setAccessible(true);
        Node[] nodesArray = (Node[]) nodesField.get(ruDataSimulator);
        assertEquals(125.00, nodesArray[0].getBandwidthAllocated());
    }

    @Test
    public void testDecreaseBandwidthAllocation() throws NoSuchFieldException, IllegalAccessException {
        Node node = new Node();
        node.setNodeId(1);
        node.setBandwidthUsage(100);
        node.setBandwidthAllocated(200);

        optimizerModule.decreaseBandwidthAllocation(node);

        // Access the nodes array using reflection and assert the change
        Field nodesField = RUDataSimulator.class.getDeclaredField("nodes");
        nodesField.setAccessible(true);
        Node[] nodesArray = (Node[]) nodesField.get(ruDataSimulator);
        assertEquals(110.00, nodesArray[0].getBandwidthAllocated());
    }

    @Test
    public void testIncreaseMemoryAllocation() throws NoSuchFieldException, IllegalAccessException {
        Node node = new Node();
        node.setNodeId(1);
        node.setMemoryUsage(50);
        node.setMemoryAllocated(100);

        optimizerModule.increaseMemoryAllocation(node);

        Field nodesField = RUDataSimulator.class.getDeclaredField("nodes");
        nodesField.setAccessible(true);
        Node[] nodesArray = (Node[]) nodesField.get(ruDataSimulator);
        assertEquals(75.00, nodesArray[0].getMemoryAllocated()); 
    }

    @Test
    public void testDecreaseMemoryAllocation() throws NoSuchFieldException, IllegalAccessException {
        Node node = new Node();
        node.setNodeId(1);
        node.setMemoryUsage(50);
        node.setMemoryAllocated(100);

        optimizerModule.decreaseMemoryAllocation(node);

        Field nodesField = RUDataSimulator.class.getDeclaredField("nodes");
        nodesField.setAccessible(true);
        Node[] nodesArray = (Node[]) nodesField.get(ruDataSimulator);
        assertEquals(60.00, nodesArray[0].getMemoryAllocated()); 
    }
    
    @Test
    public void testIncreaseCPUAllocation() throws NoSuchFieldException, IllegalAccessException {
        Node node = new Node();
        node.setNodeId(1);
        node.setCpuUsage(50);
        node.setCpuAllocated(100);

        optimizerModule.increaseCPUAllocation(node);

        Field nodesField = RUDataSimulator.class.getDeclaredField("nodes");
        nodesField.setAccessible(true);
        Node[] nodesArray = (Node[]) nodesField.get(ruDataSimulator);
        assertEquals(75.00, nodesArray[0].getCpuAllocated()); 
    }

    @Test
    public void testDecreaseCPUAllocation() throws NoSuchFieldException, IllegalAccessException {
        Node node = new Node();
        node.setNodeId(1);
        node.setCpuUsage(50);
        node.setCpuAllocated(100);

        optimizerModule.decreaseCPUAllocation(node);

        Field nodesField = RUDataSimulator.class.getDeclaredField("nodes");
        nodesField.setAccessible(true);
        Node[] nodesArray = (Node[]) nodesField.get(ruDataSimulator);
        assertEquals(60.00, nodesArray[0].getCpuAllocated()); 
    }
}


