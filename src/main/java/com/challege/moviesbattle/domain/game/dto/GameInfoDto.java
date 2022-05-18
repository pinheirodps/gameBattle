package com.challege.moviesbattle.domain.game.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GameInfoDto {

    private List<String> movies;
    private String gameId;
}
