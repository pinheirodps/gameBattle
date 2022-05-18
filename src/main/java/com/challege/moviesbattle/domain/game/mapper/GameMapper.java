package com.challege.moviesbattle.domain.game.mapper;

import com.challege.moviesbattle.domain.game.dto.GameDto;
import com.challege.moviesbattle.domain.game.dto.GameInfoDto;
import com.challege.moviesbattle.domain.game.dto.MovieDto;
import com.challege.moviesbattle.domain.game.entities.Game;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = MovieMapper.class)
public interface GameMapper {


    GameMapper INSTANCE = Mappers.getMapper(GameMapper.class);

    @Mapping(target = "gameId", source = "id")
    GameDto from (Game game);

    @Mapping(target = "movies", source = "movies")
    @Mapping(target = "gameId", source = "id")
    GameDto toGameDTO (Game game);

    @Mapping(target = "movies", source = "movies", qualifiedByName = "movies")
    GameInfoDto toGameInfoDTO (GameDto gameDto);

    @Named("movies")
    default List<String> movies(Set<MovieDto> movieDtos) {
        return movieDtos.stream()
                .map(MovieDto::getTitle)
                .collect(Collectors.toList());
    }


}
