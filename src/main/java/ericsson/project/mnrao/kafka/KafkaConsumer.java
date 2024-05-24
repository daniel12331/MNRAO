package ericsson.project.mnrao.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import ericsson.project.mnrao.models.Node;


@Service
public class KafkaConsumer {
	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

	@KafkaListener(topics="nodetopic", groupId = "myGroup")
	public void consume(Node node) {
		LOGGER.info(String.format("Node Details Recieved -> %s", node.toString()));		
	}

}
