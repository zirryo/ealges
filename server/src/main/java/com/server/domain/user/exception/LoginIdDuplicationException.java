package com.server.domain.user.exception;

import com.server.global.error.exception.ErrorCode;
import com.server.global.error.exception.InvalidValueException;

public class LoginIdDuplicationException extends InvalidValueException {

    public LoginIdDuplicationException(final String value) {
        super(value, ErrorCode.LOGIN_ID_DUPLICATION);
    }

}
