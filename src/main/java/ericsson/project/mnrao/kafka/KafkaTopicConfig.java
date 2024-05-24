package ericsson.project.mnrao.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;

public class KafkaTopicConfig {
	@Bean
	public NewTopic jsonTopic() {
		return TopicBuilder.name("nodetopic")
				.build();			
		
	}

}
