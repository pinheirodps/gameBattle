package com.challenge.moviesbattle.domain.game.services;

import com.challenge.moviesbattle.domain.game.dto.MovieDto;

import java.util.List;

/**
 * The interface Omdb client external service.
 */
public interface OmdbClientExternalService {

    /**
     * Find movie by id movie dto.
     *
     * @param id the id
     * @return the movie dto
     */
    MovieDto findMovieById(final Integer id);

    /**
     * Find movies paginate list.
     *
     * @param page the page
     * @return the list
     */
    List<MovieDto> findMoviesPaginate(final Integer page);

}
