package com.challenge.moviesbattle.domain.game.exceptions;

import com.challenge.moviesbattle.domain.commos.exceptions.AbstractCustomThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;
import java.util.Map;

public class GameOverException extends AbstractCustomThrowableProblem {

    private static final URI TYPE = URI.create("urn:movies-battle-service:problem-type:game_over");

    /**
     * Throws a {@link GameOverException}.
     *
     * @param gameId the game ended.
     */
    public GameOverException(final Long gameId) {
        super(TYPE, "Game over", Status.CONFLICT, String.format("Game was finished with gameId %s ", gameId),
            Map.of("gameId", gameId));
    }
}
