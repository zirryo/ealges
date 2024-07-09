package com.server.domain.ticket.dao;

import com.server.domain.ticket.entity.Ticket;
import com.server.domain.ticket.entity.TicketStatus;
import com.server.domain.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    Page<Ticket> findAllByUserAndStatus(User user, TicketStatus status, Pageable pageable);

}
