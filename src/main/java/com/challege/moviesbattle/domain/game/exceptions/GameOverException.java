package com.challege.moviesbattle.domain.game.exceptions;

import com.challege.moviesbattle.domain.commos.exceptions.AbstractCustomThrowableProblem;
import java.net.URI;
import java.util.Map;
import org.zalando.problem.Status;

public class GameOverException extends AbstractCustomThrowableProblem {

    private static final URI TYPE = URI.create("urn:movies-battle-serviceproblem-type:game_over");

    /**
     * Throws a {@link GameOverException}.
     *
     * @param gameId the game ended.
     */
    public GameOverException(final String gameId) {
        super(TYPE, "Game Not Found", Status.CONFLICT, String.format("Player with gameId %s has a game over", gameId),
            Map.of("gameId", gameId));
    }
}
