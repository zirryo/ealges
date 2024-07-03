package com.server.domain.user.exception;

import com.server.global.error.exception.EntityNotFoundException;

public class MemberNotFoundException extends EntityNotFoundException {

    public MemberNotFoundException(Long id) {
        super(id + " is not found");
    }

}
