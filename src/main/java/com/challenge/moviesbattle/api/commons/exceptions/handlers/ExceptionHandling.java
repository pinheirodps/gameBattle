package com.challenge.moviesbattle.api.commons.exceptions.handlers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.NativeWebRequest;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;
import org.zalando.problem.ThrowableProblem;
import org.zalando.problem.spring.web.advice.ProblemHandling;

import java.net.URI;

@ControllerAdvice
@Slf4j
public class ExceptionHandling implements ProblemHandling {

    @Override
    public ResponseEntity<Problem> handleThrowable(final Throwable throwable, final NativeWebRequest request) {
        log.error(throwable.getMessage(), throwable);

        return this.create(throwable, request);
    }

    @Override
    public ResponseEntity<Problem> handleProblem(ThrowableProblem problem, final NativeWebRequest request) {

        if (problem.getStatus() == null || Status.NOT_FOUND.getStatusCode() != problem.getStatus().getStatusCode()) {
            log.error(problem.toString(), problem);
        }

        return this.create(problem, request);
    }


    @Override
    public URI defaultConstraintViolationType() {
        return URI.create("urn:movies-battle-service:problem-type:validation_constraint");
    }

    @Override
    public boolean isCausalChainsEnabled() {
        return true;
    }

}
