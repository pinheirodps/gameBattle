package com.challenge.moviesbattle.domain.ranking.repositories;

import com.challenge.moviesbattle.domain.ranking.dto.RankingResponseDto;

import java.util.List;

public interface RankingRepository {

    List<RankingResponseDto> getRanking();;
}
