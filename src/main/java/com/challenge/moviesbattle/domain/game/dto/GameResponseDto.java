package com.challenge.moviesbattle.domain.game.dto;

import com.challenge.moviesbattle.domain.game.enums.GameStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GameResponseDto {

    private String gameId;
    private GameStatus gameStatus;
}
