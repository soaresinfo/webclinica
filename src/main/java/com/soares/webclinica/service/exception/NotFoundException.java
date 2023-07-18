package com.soares.webclinica.service.exception;

import br.com.fluentvalidator.context.ValidationResult;
import br.com.fluentvalidator.exception.ValidationException;

public class NotFoundException extends ValidationException {
    public NotFoundException(ValidationResult validationResult) {
        super(validationResult);
    }
}
