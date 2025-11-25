package com.jisdev.demo_ws.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.jisdev.demo_ws.exception.UserServiceException;
import com.jisdev.demo_ws.model.ErrorResponse;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAllExceptions(Exception ex) {
        String message = ex.getMessage();
        if (message == null || message.isEmpty()) {
            message = "Unexpected error occurred";
        }
        ErrorResponse errorDetails = ErrorResponse.builder()
                .message("An unexpected error occurred")
                .timestamp(new java.util.Date())
                .details(message)
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
    }

    @ExceptionHandler(UserServiceException.class)
    public ResponseEntity<ErrorResponse> handleSpecificExceptions(UserServiceException ex) {
        String message = ex.getMessage();
        if (message == null || message.isEmpty()) {
            message = "Unexpected service error";
        }
        ErrorResponse errorDetails = ErrorResponse.builder()
                .message("Service exception occurred")
                .timestamp(new java.util.Date())
                .details(message)
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
    }

}
