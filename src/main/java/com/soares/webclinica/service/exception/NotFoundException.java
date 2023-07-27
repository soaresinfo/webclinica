package com.soares.webclinica.service.exception;

import br.com.fluentvalidator.context.ValidationResult;
import br.com.fluentvalidator.exception.ValidationException;

import java.io.Serial;

public class NotFoundException extends ValidationException {
    @Serial
    private static final long serialVersionUID = 2476608117919452983L;

    public NotFoundException(ValidationResult validationResult) {
        super(validationResult);
    }
}
