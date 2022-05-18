package com.challege.moviesbattle.domain.game.repositories;

import com.challege.moviesbattle.domain.game.entities.Game;
import com.challege.moviesbattle.domain.player.entities.Player;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<Game, String> {
    Optional<Game> findByPlayer(final Player player);
}
