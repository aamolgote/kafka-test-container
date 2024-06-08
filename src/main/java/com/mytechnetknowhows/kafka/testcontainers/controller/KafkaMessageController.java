package com.mytechnetknowhows.kafka.testcontainers.controller;
import com.mytechnetknowhows.kafka.testcontainers.model.MessageRequest;
import com.mytechnetknowhows.kafka.testcontainers.model.MessageResponse;
import com.mytechnetknowhows.kafka.testcontainers.service.KafkaMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/message")
public class KafkaMessageController {

    @Autowired
    KafkaMessageService kafkaMessageService;

    @PostMapping("/send")
    public MessageResponse postMessage(@RequestBody MessageRequest messageRequest) {
        String response = this.kafkaMessageService.sendMessage(messageRequest);
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setMessage(response);
        return messageResponse;
    }
}
