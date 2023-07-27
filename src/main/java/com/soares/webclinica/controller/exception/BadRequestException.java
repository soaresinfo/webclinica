package com.soares.webclinica.controller.exception;

import br.com.fluentvalidator.context.ValidationResult;
import br.com.fluentvalidator.exception.ValidationException;

import java.io.Serial;

public class BadRequestException extends ValidationException {

    @Serial
    private static final long serialVersionUID = 2713556871351113033L;

    public BadRequestException(ValidationResult validationResult) {
        super(validationResult);
    }
}
