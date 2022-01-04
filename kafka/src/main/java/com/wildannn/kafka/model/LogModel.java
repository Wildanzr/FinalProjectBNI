package com.wildannn.kafka.model;

import com.wildannn.kafka.entity.Log;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class LogModel {
    private Boolean success = true;
    private String message;
    private Date timestamp = new Date();
    private List<Log> data;

    @Builder
    public LogModel(String message, List<Log> data) {
        this.message = message;
        this.data = data;
    }
}
