package com.wildannn.user.payload;

import lombok.Data;

import java.util.Date;

@Data
public class InternalError {
    private Date timestamp = new Date();
    private int status = 500;
    private String message;

    public InternalError() {
        this.message = "Internal Service Error";
    }

    public InternalError(String message) {
        this.message = message;
    }
}