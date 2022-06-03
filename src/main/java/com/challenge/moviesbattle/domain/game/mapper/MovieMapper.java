package com.challenge.moviesbattle.domain.game.mapper;

import com.challenge.moviesbattle.domain.game.dto.MovieDto;
import com.challenge.moviesbattle.domain.game.entities.Movie;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface MovieMapper {
    MovieDto from (Movie movie);

    Set<MovieDto> toMoviesDto (Set<Movie> movies);

    Set<Movie> fromMoviesDto (Set<MovieDto> movies);
}
