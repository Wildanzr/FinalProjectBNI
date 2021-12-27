package com.wildannn.poststat.payload;

import lombok.Data;

import java.util.Date;

@Data
public class InternalErr {
    private Date timestamp = new Date();
    private Integer status = 500;
    private String message;

    public InternalErr() {
        this.message = "Internal Error!";
    }

    public InternalErr(String message) {
        this.message = message;
    }
}
