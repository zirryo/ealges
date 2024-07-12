package com.server.global.error.exception;

public class InvalidOperationException extends BusinessException{

    public InvalidOperationException(String value) {
        super(value, ErrorCode.INVALID_OPERATION);
    }

}
