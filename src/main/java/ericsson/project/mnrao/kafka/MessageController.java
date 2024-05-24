package ericsson.project.mnrao.kafka;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ericsson.project.mnrao.models.Node;

@RestController
@RequestMapping("/api/v1/kafka")
public class MessageController {
	private KafkaProducer kafkaProducer;

	public MessageController(KafkaProducer kafkaProducer) {
		this.kafkaProducer = kafkaProducer;
	}
	
	@PostMapping("/publish")
	public ResponseEntity<String> publish(@RequestBody Node node){
		kafkaProducer.sendMessage(node);
		return ResponseEntity.ok("JSON Message sent to the topic");	
	}

}
