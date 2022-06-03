package com.challenge.moviesbattle.domain.jwt.exceptions;

import com.challenge.moviesbattle.domain.commos.exceptions.AbstractCustomThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;
import java.util.Map;

public class UserNotFoundException extends AbstractCustomThrowableProblem {

    private static final URI TYPE = URI.create("urn:movies-battle-service-problem-type:user_not_found");

    /**
     * Throws a {@link UserNotFoundException}.
     *
     * @param username the player Id parameter that threw the exception.
     */
    public UserNotFoundException(final String username) {
        super(TYPE, "User Not Found", Status.NOT_FOUND,
            String.format("User with username %s not found", username), Map.of("username", username));
    }

}
