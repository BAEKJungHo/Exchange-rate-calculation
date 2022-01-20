package com.wirebarley.jungho.exception;

import com.wirebarley.jungho.error.ErrorCode;
import lombok.Getter;

@Getter
public class WirebarleyException extends RuntimeException {

    private ErrorCode errorCode;

    public WirebarleyException(final ErrorCode errorCode, final String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public WirebarleyException(final ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public WirebarleyException(final String message) {
        super(message);
    }
}
