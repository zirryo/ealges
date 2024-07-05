package com.server.domain.game.dao;

import com.server.domain.game.entity.Game;
import com.server.domain.game.entity.TeamInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {

    List<Game> findByAwayTeam(String awayTeamName);

}
