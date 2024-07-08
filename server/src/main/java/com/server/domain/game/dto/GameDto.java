package com.server.domain.game.dto;

import com.server.domain.game.entity.Game;
import com.server.domain.game.entity.TeamInfo;
import com.server.domain.model.DateTime;
import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class GameDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class GameReq {

        @NotEmpty
        private TeamInfo homeTeam;

        @NotEmpty
        private TeamInfo awayTeam;

        @NotEmpty
        private DateTime startDateTime;

        @NotEmpty
        private Integer priceGrade;

        @Builder
        public GameReq(TeamInfo homeTeam, TeamInfo awayTeam, DateTime startDateTime, Integer priceGrade) {
            this.homeTeam = homeTeam;
            this.awayTeam = awayTeam;
            this.startDateTime = startDateTime;
            this.priceGrade = priceGrade;
        }

        public Game toEntity() {
            return Game.builder()
                    .homeTeamName(this.homeTeam)
                    .awayTeamName(this.awayTeam)
                    .startDateTime(this.startDateTime)
                    .priceGrade(this.priceGrade)
                    .build();
        }

    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class ModifyGameTimeReq {

        private DateTime startDateTime;

        @Builder
        public ModifyGameTimeReq(DateTime startDateTime) {
            this.startDateTime = startDateTime;
        }

    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class ModifyPriceGradeReq {
        private Integer priceGrade;

        @Builder
        public ModifyPriceGradeReq(Integer priceGrade) {
            this.priceGrade = priceGrade;
        }

    }

    @Getter
    public static class Res {

        private final String awayTeam;
        private final DateTime startDateTime;
        private final Integer priceGrade;

        public Res(Game game) {
            this.awayTeam = game.getAwayTeam().name();
            this.startDateTime = game.getStartDateTime();
            this.priceGrade = game.getPriceGrade();
        }

    }

}
