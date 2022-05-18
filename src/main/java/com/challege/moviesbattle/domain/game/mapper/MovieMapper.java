package com.challege.moviesbattle.domain.game.mapper;

import com.challege.moviesbattle.domain.game.dto.MovieDto;
import com.challege.moviesbattle.domain.game.entities.Movie;
import org.mapstruct.Mapper;

@Mapper
public interface MovieMapper {
    MovieDto from (Movie movie);
}
