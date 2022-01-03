package com.wildannn.kafka.service;

import com.wildannn.kafka.entity.Log;
import com.wildannn.kafka.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private LogRepository logRepository;

    @Value("${app.topic}")
    private String topic;

    public void produce(String message) {
        kafkaTemplate.send(topic, message);

        Log log = makeLog(message);
        logRepository.save(log);
    }

    public Log makeLog(String message) {
        return Log.builder()
                .logData(message)
                .build();
    }
}

