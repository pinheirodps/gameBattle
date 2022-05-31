package com.challege.moviesbattle.domain.movie.services;

import com.challege.moviesbattle.domain.game.dto.MovieDto;
import com.challege.moviesbattle.domain.game.fixtures.MovieFixtures;
import com.challege.moviesbattle.domain.game.services.OmdbClientExternalService;
import com.challege.moviesbattle.domain.movie.repositories.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class MovieServiceTest {
    @Mock
    MovieRepository movieRepository;
    @Mock
    OmdbClientExternalService omdbClientExternalService;
    @InjectMocks
    MovieService movieService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindMoviesPaginate() {
        when(omdbClientExternalService.findMoviesPaginate(anyInt())).thenReturn(MovieFixtures.createMoviesDto());

        List<MovieDto> result = movieService.findMoviesPaginate(1);
        assertNotNull(result);
        assertThat(result.size()).isEqualTo(2);
        verify(movieRepository, times(1)).deleteAll();
    }
}

