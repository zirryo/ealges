package com.server.global.error.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

    // Common
    INVALID_INPUT_VALUE(400, " Invalid Input Value"),
    METHOD_NOT_ALLOWED(405, " Invalid Input Value"),
    ENTITY_NOT_FOUND(400, " Entity Not Found"),
    INTERNAL_SERVER_ERROR(500, "Server Error"),
    INVALID_TYPE_VALUE(400, " Invalid Type Value"),
    HANDLE_ACCESS_DENIED(403, "Access is Denied"),

    // User
    EMAIL_DUPLICATION(400, "Email is Duplication"),
    PHONE_DUPLICATION(400, "Phone Number is Duplication"),
    LOGIN_INPUT_INVALID(400, "Login input is invalid"),

    // Ticket
    TICKET_ALREADY_USE(400, "Ticket was already used"),
    TICKET_EXPIRE(400, "Ticket was already expired"),

    // Game
    RESERVATION_NOT_AVAILABLE(400, "Reservation Not Available"),

    // Seat
    SEAT_ALREADY_SELECTED(400, "Seat already selected"),
    SEAT_TIMEOUT(400, "Seat occupancy time has ended"),
    ;

    private final int status;
    private final String message;

    ErrorCode(final int status, final String message) {
        this.status = status;
        this.message = message;
    }
}
