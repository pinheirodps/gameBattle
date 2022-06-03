package com.challenge.moviesbattle.infrasctructure.commos.configuration;

import feign.Logger;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JsonClientConfiguration {

    @Bean
    public Decoder feignEncoder() {
        return new JacksonDecoder();
    }

    @Bean
    public Encoder feignDecoder() {
        return new JacksonEncoder();
    }

    @Bean
    public Logger feignLogger() {
        return new CustomSl4jLogger();
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

}