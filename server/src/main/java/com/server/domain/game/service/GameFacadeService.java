package com.server.domain.game.service;

import com.server.domain.game.dto.GameDto;
import com.server.domain.game.entity.Game;
import com.server.domain.seat.entity.Seat;
import com.server.domain.seat.service.SeatService;
import com.server.global.error.exception.InvalidOperationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class GameFacadeService {

    private final GameService gameService;
    private final SeatService seatService;
    private final int ROW = 2;
    private final int COL = 5;
    private final String[] blockNames = {"1루(HOME)", "3루(AWAY)"};

    public void createGame(GameDto.GameReq dto) {
        gameService.createByGameDto(dto);
    }

    public Game getGame(long gameId) {
        return gameService.findById(gameId);
    }

    public void modifyStartDateTime(long gameId, GameDto.ModifyGameTimeReq dto) {
        Game game = gameService.findById(gameId);
        game.modifyStartDateTime(dto);
        gameService.updateGame(game);
    }

    public void modifyPriceGrade(long gameId, GameDto.ModifyPriceGradeReq dto) {
        Game game = gameService.findById(gameId);
        game.modifyPriceGrade(dto);
        gameService.updateGame(game);
    }

    public void deleteGame(long gameId) {
        Game game = gameService.findById(gameId);
        gameService.deleteGame(game);
    }

    public void registerAllSeat(long gameId) {
        Game game = gameService.findById(gameId);

        if(isSeatRegistered(game)) {
            throw new InvalidOperationException("이미 좌석이 등록된 경기입니다.");
        }

        int seatLogicalNum = 1;

        for (String blockName : blockNames) {
            for (int i = 1; i <= ROW; i++) {
                for (int j = 1; j <= COL; j++) {
                    Seat seat = Seat.builder()
                            .seatLogicalId(seatLogicalNum++).game(game).blockName(blockName).build();
                    seatService.createSeat(seat);
                    game.addSeat(seat);
                }
            }
        }

    }

    private boolean isSeatRegistered(Game game) {
        return !game.getSeats().isEmpty();
    }

}
