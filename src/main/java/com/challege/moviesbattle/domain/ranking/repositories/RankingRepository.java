package com.challege.moviesbattle.domain.ranking.repositories;

import com.challege.moviesbattle.domain.ranking.dto.RankingResponseDto;

import java.util.List;

public interface RankingRepository {

    List<RankingResponseDto> getRanking();;
}
