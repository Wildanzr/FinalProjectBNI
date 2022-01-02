package com.wildannn.post.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class ValidatorHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> errors = new LinkedHashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();

            errors.put(fieldName, message);
        }));
        errors.put("status", "400");

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
