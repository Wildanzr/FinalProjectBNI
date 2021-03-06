package com.wildannn.kafka.service;

import com.wildannn.kafka.entity.Log;
import com.wildannn.kafka.repository.LogRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
@Log4j2
public class KafkaProducer {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private LogRepository logRepository;

    @Value("${app.topic}")
    private String topic;

    public void produce(String message) {
        ListenableFuture<SendResult<String, String>> future =
                kafkaTemplate.send(topic, message);

        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

            @Override
            public void onSuccess(SendResult<String, String> result) {
                log.info("Sent message=[" + message +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");

                Log log = makeLog(message);
                logRepository.save(log);
            }
            @Override
            public void onFailure(Throwable ex) {
                log.info("Unable to send message=["
                        + message + "] due to : " + ex.getMessage());
            }
        });
    }

    public Log makeLog(String message) {
        return Log.builder()
                .logData(message)
                .build();
    }
}

