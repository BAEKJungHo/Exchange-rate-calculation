package com.wirebarley.jungho.error;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.validation.Errors;

import java.util.List;

@RequiredArgsConstructor
@Value
public class ErrorResponse {

    String message;
    int status;
    List<ErrorField> errors;
    String code;

    static ErrorResponse of(final ErrorCode errorCode) {
        return new ErrorResponse(errorCode);
    }

    static ErrorResponse of(final ErrorCode errorCode, final String message) {
        return new ErrorResponse(errorCode, message);
    }

    static ErrorResponse of(final ErrorCode errorCode, final Errors errors) {
        return new ErrorResponse(errorCode, ErrorField.of(errors));
    }

    private ErrorResponse(final ErrorCode errorCode) {
        this(errorCode.getMessage(), errorCode.getStatus().value(), List.of(), errorCode.getCode());
    }

    private ErrorResponse(final ErrorCode errorCode, final String message) {
        this(message, errorCode.getStatus().value(), List.of(), errorCode.getCode());
    }

    private ErrorResponse(final ErrorCode errorCode, final List<ErrorField> errorFields) {
        this(errorCode.getMessage(), errorCode.getStatus().value(), errorFields, errorCode.getCode());
    }
}
