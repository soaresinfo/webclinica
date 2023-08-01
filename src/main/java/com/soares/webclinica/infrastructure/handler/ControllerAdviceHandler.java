package com.soares.webclinica.infrastructure.handler;

import br.com.fluentvalidator.context.Error;
import br.com.fluentvalidator.context.ValidationResult;
import com.soares.webclinica.controller.exception.BadRequestException;
import com.soares.webclinica.service.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@RestControllerAdvice
public class ControllerAdviceHandler {

    private final Logger LOGGER = LoggerFactory.getLogger(ControllerAdviceHandler.class);

    @ExceptionHandler
    ResponseEntity<Object> handleException(final Exception ex, final WebRequest request){
        LOGGER.error(String.format("Falha desconhecida: %s", ex.getMessage()));
        ValidationResult result = ValidationResult.fail(List.of(Error.create("Erro desconhecido", ex.getMessage(), "500", null)));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
    }

    @ExceptionHandler
    ResponseEntity<Object> handleException(final NotFoundException nfe, final WebRequest request){
        LOGGER.error(String.format("Recurso não encontrado: %s", nfe.getMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(nfe.getValidationResult());
    }

    @ExceptionHandler
    ResponseEntity<Object> handleException(final BadRequestException bre, final WebRequest request){
        LOGGER.error(String.format("Requisição incorreta: %s", bre.getMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bre.getValidationResult());
    }
}
