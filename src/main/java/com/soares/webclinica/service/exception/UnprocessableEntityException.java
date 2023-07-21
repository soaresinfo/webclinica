package com.soares.webclinica.service.exception;

import br.com.fluentvalidator.context.ValidationResult;
import br.com.fluentvalidator.exception.ValidationException;

import java.io.Serial;

public class UnprocessableEntityException extends ValidationException {

    @Serial
    private static final long serialVersionUID = -757116700610651190L;

    public UnprocessableEntityException(ValidationResult validationResult) {
        super(validationResult);
    }
}
