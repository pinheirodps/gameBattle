package com.challenge.moviesbattle.domain.game.repositories;

import com.challenge.moviesbattle.domain.game.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface Game repository.
 */
public interface GameRepository extends JpaRepository<Game, Long> {
}
