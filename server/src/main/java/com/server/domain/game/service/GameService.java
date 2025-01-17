package com.server.domain.game.service;

import com.server.domain.game.dao.GameRepository;
import com.server.domain.game.dto.GameDto;
import com.server.domain.game.entity.Game;
import com.server.domain.game.exception.GameNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;

    public void createByGameDto(GameDto.GameReq dto) {
        gameRepository.save(dto.toEntity());
    }

    @Transactional(readOnly = true)
    public Game findById(Long id) {
        final Optional<Game> game = gameRepository.findById(id);
        game.orElseThrow(() -> new GameNotFoundException(id));
        return game.get();
    }

    public void updateGame(Game game) {
        gameRepository.save(game);
    }

    public void deleteGame(Game game) {
        gameRepository.delete(game);
    }

}
