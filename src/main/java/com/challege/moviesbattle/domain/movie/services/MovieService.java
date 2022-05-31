package com.challege.moviesbattle.domain.movie.services;

import com.challege.moviesbattle.domain.game.dto.MovieDto;
import com.challege.moviesbattle.domain.game.services.OmdbClientExternalService;
import com.challege.moviesbattle.domain.movie.repositories.MovieRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * The type Movie service.
 */
@Service
@AllArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final OmdbClientExternalService omdbClientExternalService;

    /**
     * Find movies paginate list.
     *
     * @param page the page
     * @return the list
     */
    public List<MovieDto> findMoviesPaginate(final Integer page){
            movieRepository.deleteAll();
        return omdbClientExternalService.findMoviesPaginate(page);
    }

}
