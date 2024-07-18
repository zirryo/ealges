package com.server.domain.ticket.dto;

import com.server.domain.seat.entity.Seat;
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
        private final Seat seat;

        public Res(Ticket ticket) {
            this.ticketId = ticket.getTicketId();
            this.status = ticket.getStatus();
            this.seat = ticket.getSeat();
        }

    }

}
