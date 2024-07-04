package com.server.domain.user.exception;

import com.server.global.error.exception.EntityNotFoundException;

public class LoginIdNotFoundException extends EntityNotFoundException {

    public LoginIdNotFoundException(String loginId) {
        super(loginId + " is not found");
    }

}
