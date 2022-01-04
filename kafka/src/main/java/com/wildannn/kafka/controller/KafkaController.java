package com.wildannn.kafka.controller;

import com.wildannn.kafka.entity.Log;
import com.wildannn.kafka.model.LogModel;
import com.wildannn.kafka.repository.LogRepository;
import com.wildannn.kafka.service.KafkaConsumer;
import com.wildannn.kafka.service.KafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kafka")
@RequiredArgsConstructor
public class KafkaController {
    @Autowired
    private KafkaConsumer consumer;

    @Autowired
    private KafkaProducer producer;

    @Autowired
    private LogRepository logRepository;

    @PostMapping("/send")
    public void send(@RequestBody String data) {
        producer.produce(data);
    }

    @GetMapping("/receive")
    ResponseEntity<?> getLog() {
        List<Log> logs = logRepository.findAll();
        LogModel model = LogModel.builder()
                .message("Success get all logs")
                .data(logs)
                .build();

        return ResponseEntity.ok(model);
    }

    public KafkaConsumer getConsumer() {
        return consumer;
    }

    public void setConsumer(KafkaConsumer consumer) {
        this.consumer = consumer;
    }

    public KafkaProducer getProducer() {
        return producer;
    }

    public void setProducer(KafkaProducer producer) {
        this.producer = producer;
    }
}
