package com.server.domain.ticket.controller;

import com.server.domain.ticket.dto.TicketDto;
import com.server.domain.ticket.entity.Ticket;
import com.server.domain.ticket.service.TicketFacadeService;
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

    private final TicketFacadeService ticketFacadeService;

    @PostMapping
    public ResponseEntity createTicket(@Valid @RequestBody TicketDto.Req dto, @RequestParam long userId) {
        ticketFacadeService.reserveTicket(dto, userId);
        String response = "티켓이 예매되었습니다.";
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity cancelTicket(@PathVariable("id") long ticketId) {
        ticketFacadeService.cancelTicket(ticketId);
        String response = String.format("티켓번호 %d번 티켓이 취소되었습니다.", ticketId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getTickets(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "true") boolean completed, @RequestParam long userId) {
        int defaultPageSize = 5;

        Page<Ticket> ticketPage = ticketFacadeService.findTickets(userId, completed, page, defaultPageSize);
        List<Ticket> ticketList = ticketPage.getContent();
        return new ResponseEntity<>(new MultiResponseDto<>(ticketList, ticketPage), HttpStatus.OK);
    }

}
