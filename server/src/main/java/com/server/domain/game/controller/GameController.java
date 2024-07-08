package com.server.domain.game.controller;

import com.server.domain.game.dto.GameDto;
import com.server.domain.game.service.GameService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/games")
@RequiredArgsConstructor
@Validated
public class GameController {

    private final GameService gameService;

    @PostMapping
    public ResponseEntity createGame(@Valid @RequestBody GameDto.GameReq dto) {
        gameService.createGame(dto);
        String response = "경기가 생성되었습니다.";
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity getGame(@PathVariable Long id) {
        GameDto.Res response = new GameDto.Res(gameService.findById(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/time")
    public ResponseEntity updateDateTime(@RequestParam Long gameId, @Valid @RequestBody GameDto.ModifyGameTimeReq dto) {
        gameService.modifyGameStartDateTime(gameId, dto);
        String response = "경기 시간이 변경되었습니다.";
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/grade")
    public ResponseEntity updatePriceGrade(@RequestParam Long gameId, @Valid @RequestBody GameDto.ModifyPriceGradeReq dto) {
        gameService.modifyPriceGrade(gameId, dto);
        String response = String.format("가격 등급이 %d 구간으로 변경되었습니다.", dto.getPriceGrade());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteGame(@PathVariable Long id) {
        gameService.deleteGame(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
