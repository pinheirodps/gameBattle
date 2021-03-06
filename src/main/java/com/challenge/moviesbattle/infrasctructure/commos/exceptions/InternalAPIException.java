package com.challenge.moviesbattle.infrasctructure.commos.exceptions;

import com.challenge.moviesbattle.domain.commos.exceptions.AbstractCustomThrowableProblem;
import org.zalando.problem.Status;
import org.zalando.problem.ThrowableProblem;

import java.net.URI;
import java.util.Map;

public class InternalAPIException extends AbstractCustomThrowableProblem {

    private static final URI TYPE = URI.create("urn:movive-battle:problem-type:internal_problem");

    public InternalAPIException(String detail, Map<String, Object> parameters) {
        super(TYPE, "Error invoking External API", Status.INTERNAL_SERVER_ERROR, detail, parameters);
    }

    /**
     * Throws a problem for Unchecked exception.  {@link InternalAPIException}.
     *
     * @param problem the problem
     */
    public InternalAPIException(final ThrowableProblem problem) {
        super(TYPE, "Internal API Error", Status.INTERNAL_SERVER_ERROR, "Error invoking Internal API", null);
    }

}
