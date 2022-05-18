package com.challege.moviesbattle.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {

    /**
     * Open API documentation.
     *
     * @return the {@link OpenAPI} information
     */
    @Bean
    public OpenAPI api() {
        return new OpenAPI()
            .info(new Info().title("Movies Battle Service API").version("1.0"));
    }
}
