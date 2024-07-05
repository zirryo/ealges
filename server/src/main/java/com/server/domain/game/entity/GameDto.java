package com.server.domain.game.entity;

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
    public static class ModifyGameTime {

        private DateTime startDateTime;

        @Builder
        public ModifyGameTime(DateTime startDateTime) {
            this.startDateTime = startDateTime;
        }

    }

}
