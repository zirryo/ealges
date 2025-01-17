package com.server.domain.seat.dto;

import com.server.domain.game.entity.Game;
import com.server.domain.seat.entity.Seat;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class SeatDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Req {

        @NotNull
        private Integer seatLogicalId;

        @NotNull
        private String blockName;

        private Game game;

        @Builder
        public Req(Integer seatLogicalId, String blockName, Game game) {
            this.seatLogicalId = seatLogicalId;
            this.blockName = blockName;
            this.game = game;
        }

        public Seat toEntity() {
            return Seat.builder()
                    .seatLogicalId(this.seatLogicalId)
                    .blockName(this.blockName)
                    .game(this.game)
                    .build();
        }

    }

    @Getter
    public static class Res {

        private final long seatLogicalId;
        private final String blockName;

        public Res(Seat seat) {
            this.seatLogicalId = seat.getSeatLogicalId();
            this.blockName = seat.getBlockName();
        }

    }

}
