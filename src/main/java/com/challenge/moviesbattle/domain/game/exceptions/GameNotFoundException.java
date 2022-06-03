package com.challenge.moviesbattle.domain.game.exceptions;

import com.challenge.moviesbattle.domain.commos.exceptions.AbstractCustomThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;
import java.util.Map;

public class GameNotFoundException extends AbstractCustomThrowableProblem {

    private static final URI TYPE = URI.create("urn:movies-battle-service:problem-type:player_not_found");

    /**
     * Throws a {@link GameNotFoundException}.
     *
     * @param gameId the game parameter that threw the exception.
     */
    public GameNotFoundException(final Long gameId) {
        super(TYPE, "Game Not Found", Status.NOT_FOUND,
            String.format("Game with gameId %s not found", gameId), Map.of("gameId", gameId));
    }

}
