package com.challege.moviesbattle.infrasctructure.omdb.mapper;

import com.challege.moviesbattle.domain.game.dto.MovieDto;
import com.challege.moviesbattle.infrasctructure.omdb.dtos.MovieInfraDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OmdbClientMapper {

    OmdbClientMapper INSTANCE = Mappers.getMapper(OmdbClientMapper.class);

    MovieDto moviesInfraToMoviesDto(MovieInfraDto source);
    List<MovieDto> toMovieDtoList(List<MovieInfraDto> source);
}
