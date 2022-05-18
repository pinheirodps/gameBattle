package com.challege.moviesbattle.domain.game.dto;

import java.util.List;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameDto {

    private Set<MovieDto> movies;
    private String gameId;
}
