package com.challege.moviesbattle.infrasctructure;

import com.challege.moviesbattle.domain.game.dto.MovieDto;
import com.challege.moviesbattle.domain.game.services.OmdbClientExternalService;
import com.challege.moviesbattle.infrasctructure.omdb.OmdbClientService;
import com.challege.moviesbattle.infrasctructure.omdb.mapper.OmdbClientMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Omdb client service.
 */
@Slf4j
@Service
@AllArgsConstructor
public class OmdbClientServiceImpl implements OmdbClientExternalService {

    private final OmdbClientService omdbClientService;

    private final OmdbClientMapper mapper;


    @Override
    public List<MovieDto> findMoviesPaginate(final Integer page) {
        return mapper.toMovieDtoList(omdbClientService.search(page));
    }
    @Override
    public MovieDto findMovieById(final Integer id) {
        return mapper.moviesInfraToMoviesDto(omdbClientService.findMovieById(id));
    }


}
