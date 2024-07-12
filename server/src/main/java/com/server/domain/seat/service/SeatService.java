package com.server.domain.seat.service;

import com.server.domain.seat.dao.SeatRepository;
import com.server.domain.seat.entity.Seat;
import com.server.global.error.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class SeatService {

    private final SeatRepository seatRepository;

    @Transactional(readOnly = true)
    public Seat findById(Long id) {
        final Optional<Seat> seat = seatRepository.findById(id);
        seat.orElseThrow(() -> new EntityNotFoundException(id.toString()));
        return seat.get();
    }

    public void createSeat(Seat seat) {
        seatRepository.save(seat);
    }

}
