package ericsson.project.mnrao.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import ericsson.project.mnrao.models.Node;

@Service
public class KafkaProducer {

	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);
	
	private KafkaTemplate<String, Node> kafkaTemplate;

	public KafkaProducer(KafkaTemplate<String, Node> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}
	
	public void sendMessage(Node data) {
		
		LOGGER.info(String.format("Node Details sent -> %s", data.toString()));
		
		Message<Node> message = MessageBuilder
				.withPayload(data)
				.setHeader(KafkaHeaders.TOPIC, "nodetopic")
				.build();
		
		//kafkaTemplate.send(message);
	}
}

