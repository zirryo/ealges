package com.server.domain.ticket.service;

import com.server.domain.ticket.dao.TicketRepository;
import com.server.domain.ticket.dto.TicketDto;
import com.server.domain.ticket.entity.Ticket;
import com.server.global.error.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;

    public void createTicket(TicketDto.Req dto) {
        ticketRepository.save(dto.toEntity());
    }

    @Transactional(readOnly = true)
    public Ticket findById(Long id) {
        final Optional<Ticket> ticket = ticketRepository.findById(id);
        ticket.orElseThrow(() -> new EntityNotFoundException(id + " not found"));
        return ticket.get();
    }

    public void cancelTicket(Long id) {
        final Ticket ticket = findById(id);
        ticket.modifyTicketStatus();
        ticketRepository.save(ticket);
    }

}
