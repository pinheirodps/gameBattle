package com.challenge.moviesbattle.domain.ranking.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class RankingResponseDto {

    private String name;
    private Long score;

}
