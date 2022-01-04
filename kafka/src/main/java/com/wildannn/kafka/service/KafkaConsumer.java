package com.wildannn.kafka.service;

import com.wildannn.kafka.entity.Log;
import com.wildannn.kafka.repository.LogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class KafkaConsumer {

    private final String groupId = "crowdacademyGroup";
    private final String topic = "crowdacademy";

    @Autowired
    private final KafkaProducer producer;
    private final LogRepository logRepository;

    public static List<String> messages = new ArrayList<>();



    @KafkaListener(topics = topic, groupId = groupId)
    public void listen(String message) {
        log.info("POST");
        messages.add(message);
        Log log = producer.makeLog(message);
        logRepository.save(log);
    }
}

