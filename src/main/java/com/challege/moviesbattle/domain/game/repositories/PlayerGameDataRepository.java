package com.challege.moviesbattle.domain.game.repositories;

import com.challege.moviesbattle.domain.game.entities.Game;
import com.challege.moviesbattle.domain.game.entities.PlayerGameData;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface PlayerGameDataRepository extends CrudRepository<PlayerGameData, Long> {

    Optional<PlayerGameData>findByGame(Game game);
}
