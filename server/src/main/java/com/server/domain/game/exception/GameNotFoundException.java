package com.server.domain.game.exception;

import com.server.global.error.exception.EntityNotFoundException;

public class GameNotFoundException extends EntityNotFoundException {

    public GameNotFoundException(long gameId) {
        super(gameId + " game not found");
    }
}
