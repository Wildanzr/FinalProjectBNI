package com.wildannn.user.payload;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
public class ValidatorResponse {
    private Boolean success = false;
    private String message;
    private Date timestamp = new Date();
    private Map<String, String> messages;

    @Builder
    public ValidatorResponse(String message, Map<String, String> messages) {
        this.message = message;
        this.messages = messages;
    }
}
