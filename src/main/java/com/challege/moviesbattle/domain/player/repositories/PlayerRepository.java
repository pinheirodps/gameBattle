package com.challege.moviesbattle.domain.player.repositories;

import com.challege.moviesbattle.domain.player.entities.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * The interface Player repository.
 */
public interface PlayerRepository extends JpaRepository<Player, Long> {

    /**
     * Find by id optional.
     *
     * @param id the id
     * @return the optional
     */
    @Query("SELECT p FROM Player p WHERE p.userId = :id")
    Optional<Player> findById(@Param("id") String id);

    /**
     * Find by username optional.
     *
     * @param username the username
     * @return the optional
     */
    Optional<Player> findByUsername(String username);


}
