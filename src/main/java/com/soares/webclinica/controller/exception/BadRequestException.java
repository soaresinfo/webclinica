package com.soares.webclinica.controller.exception;

import br.com.fluentvalidator.context.ValidationResult;
import br.com.fluentvalidator.exception.ValidationException;

public class BadRequestException extends ValidationException {

    public BadRequestException(ValidationResult validationResult) {
        super(validationResult);
    }
}
