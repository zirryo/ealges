package com.server.domain.ticket.dto;

import com.server.domain.seat.entity.Seat;
import com.server.domain.ticket.entity.Ticket;
import com.server.domain.ticket.entity.TicketStatus;
import com.server.domain.user.entity.User;
import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class TicketDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Req {

        @NotEmpty
        private Long gameId;

        @NotEmpty
        private Seat seat;

        @NotEmpty
        private User user;

        @NotEmpty
        private String billType;

        @Builder
        public Req(Long gameId, User user, Seat seat, String billType) {
            this.gameId = gameId;
            this.user = user;
            this.seat = seat;
            this.billType = billType;
        }

        public Ticket toEntity() {
            return Ticket.builder()
                    .gameId(this.gameId)
                    .seat(this.seat)
                    .user(this.user)
                    .billType(this.billType)
                    .build();
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
