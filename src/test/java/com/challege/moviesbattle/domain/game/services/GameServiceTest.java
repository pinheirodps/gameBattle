package com.challege.moviesbattle.domain.game.services;

import com.challege.moviesbattle.domain.game.dto.*;
import com.challege.moviesbattle.domain.game.entities.Game;
import com.challege.moviesbattle.domain.game.enums.GameStatus;
import com.challege.moviesbattle.domain.game.exceptions.GameNotFoundException;
import com.challege.moviesbattle.domain.game.exceptions.GameOverException;
import com.challege.moviesbattle.domain.game.fixtures.GameFixtures;
import com.challege.moviesbattle.domain.game.fixtures.MovieFixtures;
import com.challege.moviesbattle.domain.game.fixtures.PlayerFixtures;
import com.challege.moviesbattle.domain.game.mapper.GameMapper;
import com.challege.moviesbattle.domain.game.mapper.MovieMapper;
import com.challege.moviesbattle.domain.game.repositories.GameRepository;
import com.challege.moviesbattle.domain.movie.repositories.MovieRepository;
import com.challege.moviesbattle.domain.movie.services.MovieService;
import com.challege.moviesbattle.domain.player.dto.PlayDto;
import com.challege.moviesbattle.domain.player.entities.Player;
import com.challege.moviesbattle.domain.player.exceptions.PlayerNotFoundException;
import com.challege.moviesbattle.domain.player.repositories.PlayerRepository;
import com.challege.moviesbattle.domain.player.services.PlayerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class GameServiceTest {

    @Mock
    MovieService movieService;
    @Mock
    OmdbClientExternalService omdbClientExternalService;
    @Mock
    GameRepository gameRepository;
    @Mock
    PlayerService playerService;
    @Mock
    PlayerRepository playerRepository;
    @Mock
    MovieRepository movieRepository;

    @Mock
    private GameMapper gameMapper;

    @Mock
    private MovieMapper movieMapper;


    @InjectMocks
    GameService gameService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateGame() {

        Game gameOpen = GameFixtures.createGameOpen();
        when(playerRepository.findByUsername(anyString())).thenReturn(Optional.of(PlayerFixtures.createPlayer()));
        when(movieService.findMoviesPaginate(1)).thenReturn(MovieFixtures.createMoviesDto());
        when(gameMapper.toResponseDto(any())).thenReturn(GameResponseDto.builder().gameId("1").build());
        when(gameRepository.findById(any())).thenReturn(Optional.of(gameOpen));

        GameResponseDto game = gameService.createGame(new CreateGameDto("Danilo" ));
        assertNotNull(game);
        assertThat(game.getGameId()).isEqualTo("1");
        verify(gameRepository, times(1)).save(any(Game.class));
        verify(movieRepository, times(1)).saveAll(anySet());
        verify(movieService, times(1)).findMoviesPaginate(eq(1));
    }

    @Test
    void testCreateGamePlayerNotFound() {

        when(playerRepository.findByUsername(anyString())).thenReturn(Optional.empty());
        assertThrows(PlayerNotFoundException.class,
                () -> gameService.createGame(new CreateGameDto("Danilo" )));
    }


    @ParameterizedTest
    @CsvSource({"Batman", "Superman"})
    void testPlay(final String movieTitle) {
        Game gamePlaying = GameFixtures.createGamePlaying();
        when(playerRepository.findByUsername(any())).thenReturn(Optional.of(PlayerFixtures.createPlayer()));
        when(movieRepository.findMovieByTitle(any())).thenReturn(Optional.of(MovieFixtures.createMovie()));
        when(movieRepository.findAll()).thenReturn(new ArrayList<>(MovieFixtures.createMoviesDao()));
        when(gameMapper.toGameDTO(gamePlaying)).thenReturn(GameDto.builder().gameId("1")
                .movies(new HashSet<>(MovieFixtures.createMoviesDto())).build());
        when(gameMapper.toGameInfoDTO(any())).thenReturn(GameInfoDto.builder().gameId("1")
                .movies(new HashSet<>(MovieFixtures.createMovies())).build());


        if (movieTitle.equals("Superman")){
            gamePlaying.setCurrentRound(4);
            when(gameRepository.findById(any())).thenReturn(Optional.of(gamePlaying));

            assertThrows(GameOverException.class,
                    () ->   gameService.play(1l, new PlayDto("Danilo", movieTitle)));
            verify(gameRepository, times(1)).save(any(Game.class));

        } else {
            when(gameRepository.findById(any())).thenReturn(Optional.of(gamePlaying));
            GameInfoDto result = gameService.play(1l, new PlayDto("Danilo", movieTitle));
            assertNotNull(result);
            assertThat(result.getGameId()).isEqualTo("1");
            assertThat(result.getMovies()).isEqualTo(new HashSet<>(MovieFixtures.createMovies()));
            verify(playerRepository, times(1)).save(any(Player.class));
        }
        verify(movieService, times(1)).findMoviesPaginate(eq(1));
        verify(movieRepository, times(1)).saveAll(anySet());

    }

    @Test
    void testGetGame() {
        Game gamePlaying = GameFixtures.createGamePlaying();
        when(gameRepository.findById(any())).thenReturn(Optional.of(gamePlaying));
        when(movieRepository.findMovieByTitle(any())).thenReturn(Optional.of(MovieFixtures.createMovie()));
        when(movieRepository.findAll()).thenReturn(new ArrayList<>(MovieFixtures.createMoviesDao()));
        when(gameMapper.toGameDTO(gamePlaying)).thenReturn(GameDto.builder().gameId("1")
                .movies(new HashSet<>(MovieFixtures.createMoviesDto())).build());
        when(gameMapper.toGameInfoDTO(any())).thenReturn(GameInfoDto.builder().gameId("1")
                .movies(new HashSet<>(MovieFixtures.createMovies())).build());
        when(movieMapper.toMoviesDto(any())).thenReturn(new HashSet<>(MovieFixtures.createMoviesDto()));

        GameInfoDto result = gameService.getGame( 1l);
        assertNotNull(result);
        assertThat(result.getGameId()).isEqualTo("1");
        assertThat(result.getMovies()).isEqualTo(new HashSet<>(MovieFixtures.createMovies()));
    }

    @ParameterizedTest
    @ValueSource(longs = {1, 2})
    public void testGameOver(final Long gameId){
        when(gameRepository.findById(1l)).thenReturn(Optional.of(GameFixtures.createGameEnded()));
        when(gameMapper.toGameOverDto(any())).thenReturn(GameFixtures.createGameOverDto());

        if (gameId.equals(1l)){
            GameOverDto result = gameService.gameOver(gameId);
            assertNotNull(result);
           assertThat(result.getGameId()).isEqualTo("1");
           assertThat(result.getGameStatus()).isEqualTo(GameStatus.ENDED);
           verify(gameRepository, times(1)).save(any(Game.class));
       }else{
           assertThrows(GameNotFoundException.class,
                   () ->   gameService.gameOver(gameId));
       }

    }
}

