package com.server.domain.seat.exception;

import com.server.global.error.exception.BusinessException;
import com.server.global.error.exception.ErrorCode;

public class SeatPreemptedException extends BusinessException {

    public SeatPreemptedException(String message) {
        super(message + "번 좌석은 이미 선점된 좌석입니다.", ErrorCode.SEAT_ALREADY_SELECTED);
    }
}
