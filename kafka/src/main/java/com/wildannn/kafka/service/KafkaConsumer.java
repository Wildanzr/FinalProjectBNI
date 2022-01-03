package com.wildannn.kafka.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KafkaConsumer {
    private final static String topic = "testTopic";
    private final static String groupId = "testGroup";
    public static List<String> messages = new ArrayList<>();

    @KafkaListener(topics = topic, groupId = groupId)
    public void listen(String message) {
        messages.add(message);
    }
}

