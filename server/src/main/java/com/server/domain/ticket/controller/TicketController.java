package com.server.domain.ticket.controller;

import com.server.domain.ticket.dto.TicketDto;
import com.server.domain.ticket.entity.Ticket;
import com.server.domain.ticket.service.TicketService;
import com.server.domain.user.entity.User;
import com.server.domain.user.service.UserService;
import com.server.global.common.dto.MultiResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
@RequiredArgsConstructor
@Validated
public class TicketController {

    private final TicketService ticketService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity createTicket(@Valid @RequestBody TicketDto.Req dto) {
        ticketService.createTicket(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity cancelTicket(@PathVariable("id") long ticketId) {
        ticketService.cancelTicket(ticketId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getTickets(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "true") boolean completed) {
        User loginUser = userService.getLoginUser();
        int defaultPageSize = 5;
        Page<Ticket> ticketPage = ticketService.findTickets(loginUser, completed, page, defaultPageSize);
        List<Ticket> ticketList = ticketPage.getContent();
        return new ResponseEntity<>(new MultiResponseDto<>(ticketList, ticketPage), HttpStatus.OK);
    }

}
