package com.dev.inventoryservice.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public Map<String, String> resourceNotFoundHandler(ResourceNotFoundException resourceNotFoundException){
        Map<String, String> exceptionMsg = new HashMap<>();
        exceptionMsg.put("message", resourceNotFoundException.getMessage());
        return exceptionMsg;
    }
}
