package com.challege.moviesbattle.domain.ranking.services;

import com.challege.moviesbattle.domain.ranking.dto.RankingResponseDto;
import com.challege.moviesbattle.domain.ranking.repositories.RankingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Ranking services.
 */
@Service
@AllArgsConstructor
public class RankingServices {

    private final RankingRepository rankingRepository;

    /**
     * Get ranking list.
     *
     * @return the list Ranking Response Dto.
     */
    public List<RankingResponseDto> getRanking(){
        return  rankingRepository.getRanking();
    }
}
