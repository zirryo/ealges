package com.server.domain.game.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TeamInfo {

    EAGLES(0, "한화 이글스"),
    TWINS(1, "LG 트윈스"),
    WIZ(2, "KT 위즈"),
    LANDERS(3, "SSG 랜더스"),
    DINOS(4, "NC 다이노스"),
    BEARS(5, "두산 베어스"),
    TIGERS(6, "기아 타이거즈"),
    GIANTS(7, "롯데 자이언츠"),
    LIONS(8, "삼성 라이온즈"),
    HEROES(9, "키움 히어로즈");

    private final int teamId;
    private final String teamName;

    TeamInfo(int teamId, String teamName) {
        this.teamId = teamId;
        this.teamName = teamName;
    }
}
