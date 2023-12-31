package com.soares.webclinica.infrastructure.handler;

import com.soares.webclinica.service.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ControllerAdviceHandler {

    @ExceptionHandler
    ResponseEntity<Object> handleException(final Exception ex, final WebRequest request){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

    @ExceptionHandler
    ResponseEntity<Object> handleException(final NotFoundException nfe, final WebRequest request){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(nfe.getValidationResult());
    }
}
