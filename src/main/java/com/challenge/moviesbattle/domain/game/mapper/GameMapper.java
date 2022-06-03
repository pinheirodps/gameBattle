package com.challenge.moviesbattle.domain.game.mapper;

import com.challenge.moviesbattle.domain.game.dto.GameDto;
import com.challenge.moviesbattle.domain.game.dto.GameInfoDto;
import com.challenge.moviesbattle.domain.game.dto.GameOverDto;
import com.challenge.moviesbattle.domain.game.dto.GameResponseDto;
import com.challenge.moviesbattle.domain.game.dto.MovieDto;
import com.challenge.moviesbattle.domain.game.entities.Game;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {MovieMapper.class})
public interface GameMapper {


    GameMapper INSTANCE = Mappers.getMapper(GameMapper.class);

    @Mapping(target = "gameId", source = "id")
    GameDto from (Game game);

    @Mapping(target = "gameId", source = "id")
    GameOverDto toGameOverDto (Game game);
    @Mapping(target = "gameId", source = "id")
    GameResponseDto toResponseDto (Game game);

    @Mapping(target = "gameId", source = "id")
    GameDto toGameDTO (Game game);

    @Mapping(target = "movies", source = "movies", qualifiedByName = "movies")
    GameInfoDto toGameInfoDTO (GameDto gameDto);



    @Named("movies")
    default Set<String> movies(Set<MovieDto> movieDtos) {
        Set<String> movies = movieDtos.stream()
                .map(MovieDto::getTitle)
                .collect(Collectors.toSet());
      //  Collections.shuffle(movies);
        return movies;
    }


}
