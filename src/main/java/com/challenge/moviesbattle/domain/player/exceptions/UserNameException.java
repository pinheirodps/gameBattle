package com.challenge.moviesbattle.domain.player.exceptions;

import com.challenge.moviesbattle.domain.commos.exceptions.AbstractCustomThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

/**
 * Exception for invalid username.
 */
public class UserNameException extends AbstractCustomThrowableProblem {

    private static final URI TYPE = URI.create("urn:movies-battle-service:problem-type:invalid_username");

    /**
     * Creates new instance of {@code UserNameException}.
     */
    public UserNameException() {
        super(TYPE, "Invalid Username", Status.BAD_REQUEST,
            "Username must not be null or empty", null);
    }

}
