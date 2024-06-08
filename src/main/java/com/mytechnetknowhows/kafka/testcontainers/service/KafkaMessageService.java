package com.mytechnetknowhows.kafka.testcontainers.service;
import com.mytechnetknowhows.kafka.testcontainers.model.MessageRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessageService {

    public static final String TOPIC = "message_request_multi_partition_topic";

    private final KafkaTemplate<String, MessageRequest> kafkaTemplate;
    private static final Logger logger = LoggerFactory.getLogger(KafkaMessageService.class);
    @Autowired
    public KafkaMessageService(KafkaTemplate<String, MessageRequest> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Autowired
    MessageStore messageStore;

    public String sendMessage(MessageRequest messageRequest){
        String message = null;
        try {
            kafkaTemplate.send(TOPIC, messageRequest);
            message = "Message sent";
        } catch (Exception ex){
            message = "Error occurred while sending message";
            logger.error("KafkaMessageService::sendMessage - An error occurred while sending message, detail error: ", ex);
        }
        return message;
    }



    @KafkaListener(topics = TOPIC, groupId = "test-group",
            containerFactory = "kafkaListenerContainerFactory")
    public void processMessageRequest(MessageRequest messageRequest) {
        logger.info("Received Message Request: " + messageRequest.getOrderNumber());
        messageStore.addMessage(messageRequest);
    }
}
