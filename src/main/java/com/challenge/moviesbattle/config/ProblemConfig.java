package com.challenge.moviesbattle.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zalando.problem.ProblemModule;
import org.zalando.problem.violations.ConstraintViolationProblemModule;

@Configuration
public class ProblemConfig {

    @Bean
    ProblemModule problemModule() {
        final ProblemModule module = new ProblemModule();

        // do not show stacktraces
        module.withStackTraces(false);
        return module;
    }

    @Bean
    ConstraintViolationProblemModule constraintViolationProblemModule() {
        return new ConstraintViolationProblemModule();
    }
}
