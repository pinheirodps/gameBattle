package com.challenge.moviesbattle.infrasctructure.commos.exceptions;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.zalando.problem.ThrowableProblem;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class FeignErrorDecoder implements ErrorDecoder {

    private ObjectMapper mapper;

    public FeignErrorDecoder() {
        this.mapper = new ObjectMapper().findAndRegisterModules();
    }

    private String getBody(Response response) throws IOException {
        return IOUtils.toString(response.body().asReader(StandardCharsets.UTF_8));
    }

    @Override
    public Exception decode(String methodKey, Response response) {

        String responseBody = null;
        try {
            responseBody = getBody(response);
        } catch (Exception e) {
            log.error("Error trying to process response body.", e);
        }

        try {
            if (HttpStatus.resolve(response.status()).isError()) {
                return mapper.readValue(responseBody, ThrowableProblem.class);
            }
        } catch (Exception e) {
            log.error("Error trying to convert exception to ThrowableProblem.", e);
        }

        String detail = response.reason();
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("status", response.status());
        parameters.put("reason", response.reason());
        parameters.put("httpMethod", response.request().httpMethod());
        parameters.put("url", response.request().url());
        parameters.put("body", responseBody);

        return new InternalAPIException(detail, parameters);
    }
}
