package com.server.domain.ticket.entity;

import com.server.domain.game.entity.Game;
import com.server.domain.model.Auditable;
import com.server.domain.seat.entity.Seat;
import com.server.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Ticket extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gameId")
    private Game game;

    @Enumerated(EnumType.STRING)
    private TicketStatus status;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "seatId")
    private Seat seat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    private String billType;

    @Builder
    public Ticket(Game game, Seat seat, User user, String billType) {
        this.game = game;
        this.seat = seat;
        this.user = user;
        this.status = TicketStatus.COMPLETED;
        this.billType = billType;
    }

    public void modifyTicketStatus() {
        this.status = TicketStatus.CANCELED;
    }

}
