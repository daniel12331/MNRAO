package ericsson.project.mnrao.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import ericsson.project.mnrao.models.Node;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.kafka.core.KafkaTemplate;

public class RUDataSimulatorTest {

    private static final String KAFKA_TOPIC = "node-topic";
    private static final String TASK_ID = "taskId";

    @Mock
    private KafkaTemplate<String, Node> kafkaTemplateMock;

    private RUDataSimulator ruDataSimulator;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        ruDataSimulator = new RUDataSimulator();
        ruDataSimulator.kafkaTemplate = kafkaTemplateMock;
    }

    @Test
    public void testSimulateNodeData() {
        Node node = new Node(1, 101, 10.0, 10.0, 10.0, 50.0, 50.0, 50.0);
        when(kafkaTemplateMock.send(any(), any(), any())).thenReturn(null);

        ruDataSimulator.simulateNodeData(node);
        // Verify that kafkaTemplate.send() was called with the expected arguments
        verify(kafkaTemplateMock, times(1)).send(eq(KAFKA_TOPIC), eq(TASK_ID), eq(node));
    }
    @Test
    public void testGenerateMaxLoad() {
        Node node = new Node(1, 101, 10.0, 10.0, 10.0, 50.0, 50.0, 50.0);
        ruDataSimulator.generateMaxLoad(node, "cpu");

        // Verify that CPU usage is set to allocated CPU
        assert(node.getCpuUsage() == node.getCpuAllocated());
    }

    @Test
    public void testGenerateUsageWithinBounds() {
        Node node = new Node(1, 101, 10.0, 10.0, 10.0, 50.0, 50.0, 50.0);
        double usage = ruDataSimulator.generateUsage(5.0, 10.0);

        // Verify that generated usage is within the allocated bounds
        assert(usage >= 0 && usage <= 10.0);
    }
}
