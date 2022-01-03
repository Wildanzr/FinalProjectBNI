package com.wildannn.kafka.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@NoArgsConstructor
@Document("logs")
public class Log {

    @Id
    private String id;

    @JsonProperty("executed_at")
    private Date executedAt;

    @JsonProperty("log_data")
    private String logData;

    @Builder
    public Log(String logData) {
        this.executedAt = new Date();
        this.logData = logData;
    }
}
