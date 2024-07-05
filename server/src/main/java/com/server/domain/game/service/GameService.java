package com.server.domain.game.service;

import com.server.domain.game.dao.GameRepository;
import com.server.domain.game.entity.Game;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;

    @Transactional
    public void save(Game game) {}
}
