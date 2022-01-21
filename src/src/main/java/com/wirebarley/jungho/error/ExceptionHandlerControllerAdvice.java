package com.wirebarley.jungho.error;

import com.wirebarley.jungho.exception.ValidationException;
import com.wirebarley.jungho.exception.WirebarleyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionHandlerControllerAdvice {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponse> validation(final ValidationException e) {
        log.error("Validation Exception occur", e);
        return ResponseEntity
                .status(ErrorCode.VALIDATION_ERROR.getStatus())
                .body(ErrorResponse.of(ErrorCode.VALIDATION_ERROR, e.getErrors()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> illegalArgument(final IllegalArgumentException e) {
        log.error("IllegalArgument Exception occur", e);
        return ResponseEntity
                .status(ErrorCode.BAD_REQUEST.getStatus())
                .body(ErrorResponse.of(ErrorCode.BAD_REQUEST, e.getMessage()));
    }

    @ExceptionHandler(WirebarleyException.class)
    public ResponseEntity<ErrorResponse> wirebarley(final WirebarleyException e) {
        log.error("Wirebarley Exception occur", e);
        return ResponseEntity
                .status(e.getErrorCode().getStatus())
                .body(ErrorResponse.of(e.getErrorCode()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> exception(final Exception e) {
        log.error("Not Defined or Not Handled Exception occur", e);
        return ResponseEntity
                .status(ErrorCode.INTERNAL_SERVER_ERROR.getStatus())
                .body(ErrorResponse.of(ErrorCode.INTERNAL_SERVER_ERROR));
    }
}
