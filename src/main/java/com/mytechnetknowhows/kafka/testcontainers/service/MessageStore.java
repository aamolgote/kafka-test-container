package com.mytechnetknowhows.kafka.testcontainers.service;

import com.mytechnetknowhows.kafka.testcontainers.model.MessageRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MessageStore {

    private final List<MessageRequest> messages = new ArrayList<>();

    public void addMessage(MessageRequest userEvent) {
        messages.add(userEvent);
    }

    public List<MessageRequest> getMessages() {
        return messages;
    }

    public void clear() {
        this.messages.clear();
    }
}