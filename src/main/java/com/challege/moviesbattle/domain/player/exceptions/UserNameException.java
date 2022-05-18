package com.challege.moviesbattle.domain.player.exceptions;

import com.challege.moviesbattle.domain.commos.exceptions.AbstractCustomThrowableProblem;
import java.net.URI;
import org.zalando.problem.Status;

/**
 * Exception for invalid username.
 */
public class UserNameException extends AbstractCustomThrowableProblem {

    private static final URI TYPE = URI.create("urn:movies-battle-service:problem-type:invalid_username");

    /**
     * Creates new instance of {@code UserNameException}.
     */
    public UserNameException() {
        super(TYPE, "Invalid Uservame", Status.BAD_REQUEST,
            "Username must not be null or empty", null);
    }

}
