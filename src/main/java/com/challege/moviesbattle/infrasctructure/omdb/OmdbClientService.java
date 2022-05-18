package com.challege.moviesbattle.infrasctructure.omdb;

import com.challege.moviesbattle.infrasctructure.commos.configuration.JsonClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "omdbapi", url = "${omdbapi.url}", configuration = JsonClientConfiguration.class)
public interface OmdbClientService {

    @GetMapping
    ResultSearch search(@RequestParam("t") String movieTitle);

}
