package com.challege.moviesbattle.domain.game.exceptions;

import com.challege.moviesbattle.domain.commos.exceptions.AbstractCustomThrowableProblem;
import com.challege.moviesbattle.domain.player.entities.Player;
import java.net.URI;
import java.util.Map;
import org.zalando.problem.Status;

public class GameNotFoundException extends AbstractCustomThrowableProblem {

    private static final URI TYPE = URI.create("urn:movies-battle-serviceproblem-type:player_not_found");

    /**
     * Throws a {@link GameNotFoundException}.
     *
     * @param gameId the game parameter that threw the exception.
     */
    public GameNotFoundException(final String gameId) {
        super(TYPE, "Game Not Found", Status.NOT_FOUND,
            String.format("Player with gameId %s not found", gameId), Map.of("gameId", gameId));
    }

    /**
     * Throws a {@link GameNotFoundException}.
     *
     * @param username the game parameter that threw the exception.
     */
    public GameNotFoundException(final Player player) {
        super(TYPE, "Game Not Found", Status.NOT_FOUND, String.format("Player with username %s not found", player.getUsername()),
            Map.of("username", player.getUsername()));
    }

}
