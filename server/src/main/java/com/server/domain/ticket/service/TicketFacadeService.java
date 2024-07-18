package com.server.domain.ticket.service;

import com.server.domain.game.entity.Game;
import com.server.domain.game.service.GameService;
import com.server.domain.seat.entity.Seat;
import com.server.domain.seat.service.SeatService;
import com.server.domain.ticket.dto.TicketDto;
import com.server.domain.ticket.entity.Ticket;
import com.server.domain.user.entity.User;
import com.server.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TicketFacadeService {

    private final TicketService ticketService;
    private final GameService gameService;
    private final UserService userService;
    private final SeatService seatService;

    public void reserveTicket(TicketDto.Req dto, long userId) {
        Seat seat = seatService.findById(dto.getSeatId());
        seatService.occupySeat(seat);
        Game game = gameService.findById(dto.getGameId());
        User user = userService.findById(userId);
        Ticket ticket = Ticket.builder().game(game).seat(seat).user(user).billType(dto.getBillType()).build();
        ticketService.createTicket(ticket);
        user.addTicket(ticket);
    }

    public Ticket getTicket(long ticketId) {
        return ticketService.findById(ticketId);
    }

    public void cancelTicket(long ticketId) {
        Ticket ticket = ticketService.findById(ticketId);
        ticket.modifyTicketStatus();
        seatService.releaseSeat(ticket.getSeat());
        ticketService.updateTicket(ticket);
    }

    public Page<Ticket> findTickets(long userId, boolean isCompleted, int page, int size) {
        User user = userService.findById(userId);
        return ticketService.findTicketsByUser(user, isCompleted, page, size);
    }

}
