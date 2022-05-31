package com.challege.moviesbattle.domain.ranking.services;

import com.challege.moviesbattle.domain.ranking.dto.RankingResponseDto;
import com.challege.moviesbattle.domain.ranking.repositories.RankingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;


class RankingServicesTest {
    @Mock
    RankingRepository rankingRepository;
    @InjectMocks
    RankingServices rankingServices;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetRanking() {
        when(rankingRepository.getRanking()).thenReturn(List.of(RankingResponseDto
                .builder().name("Danilo").score(3l).build()));

        List<RankingResponseDto> result = rankingServices.getRanking();
        assertNotNull(result);
        assertThat(result.size()).isEqualTo(1);
    }

}

