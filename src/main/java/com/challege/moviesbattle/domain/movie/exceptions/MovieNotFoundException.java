package com.challege.moviesbattle.domain.movie.exceptions;

import com.challege.moviesbattle.domain.commos.exceptions.AbstractCustomThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;
import java.util.Map;

public class MovieNotFoundException extends AbstractCustomThrowableProblem {

    private static final URI TYPE = URI.create("urn:movies-battle-service:problem-type:movie_not_found");

    /**
     * Throws a {@link MovieNotFoundException}.
     *
     * @param title the movie parameter that threw the exception.
     */
    public MovieNotFoundException(final String title) {
        super(TYPE, "Movie Not Found", Status.NOT_FOUND,
            String.format("Movie with title %s not found", title), Map.of("title", title));
    }


}
