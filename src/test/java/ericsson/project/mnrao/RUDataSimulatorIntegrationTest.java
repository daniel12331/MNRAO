package ericsson.project.mnrao;

import ericsson.project.mnrao.models.Node;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.KafkaTemplate;
import ericsson.project.mnrao.services.RUDataSimulator;

import static org.mockito.Mockito.*;

@SpringBootTest
public class RUDataSimulatorIntegrationTest {

    @Autowired
    private RUDataSimulator ruDataSimulator;

    @MockBean
    private KafkaTemplate<String, Node> kafkaTemplate;

    @Test
    public void testScheduleNodeData() throws InterruptedException {
        // Given
        Node node1 = new Node(1, 101, 10.0, 10.0, 10.0, 50.0, 50.0, 50.0);
        Node node2 = new Node(2, 102, 10.0, 10.0, 10.0, 50.0, 50.0, 50.0);
        Node node3 = new Node(3, 103, 10.0, 10.0, 10.0, 50.0, 50.0, 50.0);
        Node node4 = new Node(4, 104, 10.0, 10.0, 10.0, 50.0, 50.0, 50.0);
        Node node5 = new Node(5, 105, 10.0, 10.0, 10.0, 50.0, 50.0, 50.0);

        // When
        Thread.sleep(10000);

        // Then
        verify(kafkaTemplate, atLeast(2)).send(eq("node-topic"), eq("taskId"), any(Node.class));
    }
}
