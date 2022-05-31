package com.challege.moviesbattle.domain.player.exceptions;

import com.challege.moviesbattle.domain.commos.exceptions.AbstractCustomThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;
import java.util.Map;

public class PlayerNotFoundException extends AbstractCustomThrowableProblem {

    private static final URI TYPE = URI.create("urn:movies-battle-service-problem-type:player_not_found");

    /**
     * Throws a {@link PlayerNotFoundException}.
     *
     * @param playerId the player Id parameter that threw the exception.
     */
    public PlayerNotFoundException(final String playerId) {
        super(TYPE, "Player Not Found", Status.NOT_FOUND,
            String.format("Player with playerId %s not found", playerId), Map.of("playerId", playerId));
    }

}
