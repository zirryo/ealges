package com.server.domain.ticket.service;

import com.server.domain.ticket.dao.TicketRepository;
import com.server.domain.ticket.entity.Ticket;
import com.server.domain.ticket.entity.TicketStatus;
import com.server.domain.user.entity.User;
import com.server.global.error.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;

    public void createTicket(Ticket ticket) {
        ticketRepository.save(ticket);
    }

    @Transactional(readOnly = true)
    public Ticket findById(Long id) {
        final Optional<Ticket> ticket = ticketRepository.findById(id);
        ticket.orElseThrow(() -> new EntityNotFoundException(id + " not found"));
        return ticket.get();
    }

    public void updateTicket(Ticket ticket) {
        ticketRepository.save(ticket);
    }

    @Transactional(readOnly = true)
    public Page<Ticket> findTicketsByUser(User user, boolean isCompleted, int page, int size) {
        if (isCompleted) {
            return ticketRepository.findAllByUserAndStatus(user, TicketStatus.COMPLETED, PageRequest.of(page, size));
        } else {
            return ticketRepository.findAllByUserAndStatus(user, TicketStatus.CANCELED, PageRequest.of(page, size));
        }
    }

}
