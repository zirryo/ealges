package com.server.domain.game.entity;

import com.server.domain.game.dto.GameDto;
import com.server.domain.model.DateTime;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gameId;

    @Column(nullable = false, updatable = false)
    private String leagueName;

    @Column(nullable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private TeamInfo homeTeam;

    @Column(nullable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private TeamInfo awayTeam;

    @Column(nullable = false, updatable = false)
    private String fieldName;

    @Embedded
    private DateTime startDateTime;

    @Column(nullable = false)
    private Integer priceGrade;

    @Column
    private LocalDateTime timeOnSale;

    @Column
    private LocalDateTime timeOffSale;

    @Builder
    public Game(TeamInfo homeTeamName, TeamInfo awayTeamName, DateTime startDateTime, Integer priceGrade) {
        this.leagueName = "2024 KBO 리그";
        this.homeTeam = homeTeamName;
        this.awayTeam = awayTeamName;
        this.fieldName = "한화생명이글스파크";
        this.startDateTime = startDateTime;
        this.priceGrade = priceGrade;
        this.timeOnSale = startDateTime.toTimeOnSale(startDateTime);
        this.timeOffSale = startDateTime.toTimeOffSale(startDateTime);
    }

    public void modifyStartDateTime(GameDto.ModifyGameTimeReq dto) {
        this.startDateTime = dto.getStartDateTime();
        this.timeOnSale = startDateTime.toTimeOnSale(startDateTime);
        this.timeOffSale = startDateTime.toTimeOffSale(startDateTime);
    }

    public void modifyPriceGrade(GameDto.ModifyPriceGradeReq dto) {
        this.priceGrade = dto.getPriceGrade();
    }

}
