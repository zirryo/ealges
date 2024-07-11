package com.server.domain.seat.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.server.domain.game.entity.Game;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seatId;

    @Column(nullable = false)
    private Integer seatLogicalId;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gameId", nullable = false, updatable = false)
    private Game game;

    @Column(nullable = false)
    private String blockName;

    @Column(nullable = false)
    private boolean isAvailable;

    @Builder
    public Seat(Integer seatLogicalId, Game game, String blockName) {
        this.seatLogicalId = seatLogicalId;
        this.game = game;
        this.blockName = blockName;
        this.isAvailable = true;
    }

}
