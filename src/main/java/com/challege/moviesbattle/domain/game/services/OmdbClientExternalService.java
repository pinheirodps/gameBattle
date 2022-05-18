package com.challege.moviesbattle.domain.game.services;

import com.challege.moviesbattle.domain.game.dto.MovieDto;
import java.util.List;
import java.util.Set;

public interface OmdbClientExternalService {

    MovieDto findMovieByTitle(final String title);

    List<MovieDto> getRandomMovies();

}
