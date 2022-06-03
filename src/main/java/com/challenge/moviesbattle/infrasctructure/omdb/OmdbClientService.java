package com.challenge.moviesbattle.infrasctructure.omdb;

import com.challenge.moviesbattle.infrasctructure.commos.configuration.JsonClientConfiguration;
import com.challenge.moviesbattle.infrasctructure.omdb.dtos.MovieInfraDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * The interface Omdb client service.
 */
@FeignClient(value = "omdbapi", url = "${omdbapi.url}", configuration = JsonClientConfiguration.class)
public interface OmdbClientService {

    /**
     * Search list.
     *
     * @param page the page
     * @return the list
     */
    @GetMapping(consumes = "application/json")
    List<MovieInfraDto> search(@RequestParam("page")final Integer page);

    /**
     * Find movie by id movie infra dto.
     *
     * @param id the id
     * @return the movie infra dto
     */
    @GetMapping()
    MovieInfraDto findMovieById(@RequestParam("id")final Integer id);

}
