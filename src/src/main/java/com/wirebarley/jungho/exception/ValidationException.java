package com.wirebarley.jungho.exception;

import com.wirebarley.jungho.error.ErrorCode;
import lombok.Getter;
import org.springframework.validation.Errors;

@Getter
public class ValidationException extends WirebarleyException {

    private final Errors errors;

    public ValidationException(final Errors errors) {
        super(ErrorCode.VALIDATION_ERROR);
        this.errors = errors;
    }
}
