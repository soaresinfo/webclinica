package com.soares.webclinica.infrastructure.handler;

import com.soares.webclinica.controller.exception.BadRequestException;
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
        //ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

    @ExceptionHandler
    ResponseEntity<Object> handleException(final NotFoundException nfe, final WebRequest request){
        //nfe.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(nfe.getValidationResult());
    }

    @ExceptionHandler
    ResponseEntity<Object> handleException(final BadRequestException bre, final WebRequest request){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bre.getValidationResult());
    }
}
