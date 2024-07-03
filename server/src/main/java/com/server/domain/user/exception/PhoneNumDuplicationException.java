package com.server.domain.user.exception;

import com.server.global.error.exception.ErrorCode;
import com.server.global.error.exception.InvalidValueException;

public class PhoneNumDuplicationException extends InvalidValueException {

    public PhoneNumDuplicationException(final String value) {
        super(value, ErrorCode.PHONE_DUPLICATION);
    }

}
