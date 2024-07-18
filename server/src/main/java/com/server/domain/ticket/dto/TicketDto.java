package com.server.domain.ticket.dto;

import com.server.domain.game.dto.GameDto;
import com.server.domain.seat.dto.SeatDto;
import com.server.domain.ticket.entity.Ticket;
import com.server.domain.ticket.entity.TicketStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class TicketDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Req {

        @NotNull
        private Long gameId;

        @NotNull
        private Long seatId;

        @NotNull
        private String billType;

        @Builder
        public Req(Long gameId, Long seatId, String billType) {
            this.gameId = gameId;
            this.seatId = seatId;
            this.billType = billType;
        }

    }

    @Getter
    public static class Res {

        private final long ticketId;
        private final TicketStatus status;
        private final String billType;
        private final GameDto.Res gameInfo;
        private final SeatDto.Res seatInfo;


        public Res(Ticket ticket) {
            this.ticketId = ticket.getTicketId();
            this.status = ticket.getStatus();
            this.billType = ticket.getBillType();
            this.gameInfo = new GameDto.Res(ticket.getGame());
            this.seatInfo = new SeatDto.Res(ticket.getSeat());
        }

    }

}
