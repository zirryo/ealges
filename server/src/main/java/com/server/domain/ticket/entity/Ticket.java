package com.server.domain.ticket.entity;

import com.server.domain.seat.entity.Seat;
import com.server.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketId;


    private Long gameId;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Enumerated(EnumType.STRING)
    private TicketStatus status;

    @OneToOne
    @JoinColumn(name = "ticketId")
    private Seat seat;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    private String billType;

    @Builder
    public Ticket(Long gameId, Seat seat, User user, String billType) {
        this.gameId = gameId;
        this.seat = seat;
        this.user = user;
        this.status = TicketStatus.COMPLETED;
        this.billType = billType;
    }

    public void modifyTicketStatus() {
        this.status = TicketStatus.CANCELED;
    }

}
