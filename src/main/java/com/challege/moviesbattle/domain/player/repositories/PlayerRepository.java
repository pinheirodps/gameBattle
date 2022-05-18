package com.challege.moviesbattle.domain.player.repositories;

import com.challege.moviesbattle.domain.player.entities.Player;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PlayerRepository extends CrudRepository<Player, Long> {

    @Query("SELECT p FROM Player p WHERE p.userId = :id")
    Optional<Player> findById(String id);

    Optional<Player> findByUsername(String username);

}
