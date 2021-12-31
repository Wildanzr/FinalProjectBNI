package com.wildannn.user.payload;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class ErrorResponse {
    private Boolean success = false;
    private String message;
    private Date timestamp = new Date();
    private Integer status;

    @Builder
    public ErrorResponse(String message, Integer status) {
        this.message = message;
        this.status = status;
    }
}