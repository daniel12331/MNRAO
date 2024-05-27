package ericsson.project.mnrao;

import ericsson.project.mnrao.models.Node;
import ericsson.project.mnrao.services.RUDataSimulator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.kafka.core.KafkaTemplate;

import static org.mockito.Mockito.*;

public class RUDataSimulatorTest {

    @Mock
    private KafkaTemplate<String, Node> kafkaTemplate;

    @InjectMocks
    private RUDataSimulator ruDataSimulator;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGenerateMaxLoad_cpu() {
        Node node = new Node(1, 101, 10.0, 10.0, 10.0, 50.0, 50.0, 50.0);
        ruDataSimulator.generateMaxLoad(node, "cpu");
        assert(node.getCpuUsage() == node.getCpuAllocated());
    }

    @Test
    public void testGenerateMaxLoad_memory() {
        Node node = new Node(1, 101, 10.0, 10.0, 10.0, 50.0, 50.0, 50.0);
        ruDataSimulator.generateMaxLoad(node, "memory");
        assert(node.getMemoryUsage() == node.getMemoryAllocated());
    }

    @Test
    public void testGenerateMaxLoad_bandwidth() {
        Node node = new Node(1, 101, 10.0, 10.0, 10.0, 50.0, 50.0, 50.0);

        ruDataSimulator.generateMaxLoad(node, "bandwidth");
        assert(node.getBandwidthUsage() == 45.0);
    }

    @Test
    public void testGenerateMaxLoad_invalidResource() {
        Node node = new Node(1, 101, 10.0, 10.0, 10.0, 50.0, 50.0, 50.0);
        System.setOut(new java.io.PrintStream(new java.io.ByteArrayOutputStream()));
        ruDataSimulator.generateMaxLoad(node, "invalid");
        assert(node.getCpuUsage() == 10.0);
    }

    @Test
    public void testSimulateNodeData() {
        Node node = new Node(1, 101, 10.0, 10.0, 10.0, 50.0, 50.0, 50.0);
        ruDataSimulator.simulateNodeData(node);
        verify(kafkaTemplate, times(1)).send(eq("node-topic"), eq("taskId"), eq(node));
    }
}
