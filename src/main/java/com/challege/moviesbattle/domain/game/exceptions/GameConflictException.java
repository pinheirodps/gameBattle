package com.challege.moviesbattle.domain.game.exceptions;

import com.challege.moviesbattle.domain.commos.exceptions.AbstractCustomThrowableProblem;
import java.net.URI;
import java.util.Map;
import org.zalando.problem.Status;

public class GameConflictException extends AbstractCustomThrowableProblem {

    private static final URI TYPE = URI.create("urn:movies-battle-serviceproblem-type:player_conflict_game");

    /**
     * Throws a {@link GameConflictException}.
     *
     * @param gameId the game parameter that threw the exception.
     */
    public GameConflictException(final String gameId) {
        super(TYPE, "Game Not Found", Status.CONFLICT,
            String.format("Player with gameId %s has a game joined", gameId), Map.of("gameId", gameId));
    }

}
