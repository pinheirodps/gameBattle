package com.challege.moviesbattle.infrasctructure.omdb.mapper;

import com.challege.moviesbattle.domain.game.dto.MovieDto;
import com.challege.moviesbattle.infrasctructure.omdb.ResultSearch;
import com.challege.moviesbattle.infrasctructure.omdb.dtos.MovieInfraDto;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OmdbClientMapper {

    OmdbClientMapper INSTANCE = Mappers.getMapper(OmdbClientMapper.class);

    MovieDto moviesInfraToMoviesDto(ResultSearch result);
}
