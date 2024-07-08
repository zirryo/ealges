package com.server.domain.ticket.dao;

import com.server.domain.ticket.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

}
