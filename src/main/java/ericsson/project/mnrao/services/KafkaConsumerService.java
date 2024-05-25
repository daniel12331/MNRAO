//package ericsson.project.mnrao.services;
//
//import ericsson.project.mnrao.models.Node;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Component;
//
//@Component
//public class KafkaConsumerService {
//
//    private final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);
//
//    @KafkaListener(topics = {"node-topic"}, groupId = "task-group")
//    public void consume(Node node) {
//
//        //logger.info(String.format("Node status is updated nodeID: " + node.getNodeId()) );
//    }
//}