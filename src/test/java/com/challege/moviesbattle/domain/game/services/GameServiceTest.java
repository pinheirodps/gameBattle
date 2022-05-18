package com.challege.moviesbattle.domain.game.services;

import com.challege.moviesbattle.domain.game.dto.CreateGameDto;
import com.challege.moviesbattle.domain.game.dto.GameInfoDto;
import com.challege.moviesbattle.domain.game.fixtures.GameFixtures;
import com.challege.moviesbattle.domain.game.fixtures.MovieFixtures;
import com.challege.moviesbattle.domain.game.fixtures.PlayerFixtures;
import com.challege.moviesbattle.domain.game.fixtures.PlayerGameDataFixtures;
import com.challege.moviesbattle.domain.game.repositories.GameRepository;
import com.challege.moviesbattle.domain.game.repositories.MovieRepository;
import com.challege.moviesbattle.domain.game.repositories.PlayerGameDataRepository;
import com.challege.moviesbattle.domain.player.dto.CreateJoinGameDto;
import com.challege.moviesbattle.domain.player.dto.CreatePlayerDto;
import com.challege.moviesbattle.domain.player.services.PlayerService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

class GameServiceTest {

    @Mock
    OmdbClientExternalService omdbClientExternalService;
    @Mock
    GameRepository gameRepository;
    @Mock
    PlayerService playerService;
    @Mock
    PlayerGameDataRepository playerGameDataRepository;
    @Mock
    MovieRepository movieRepository;
    @InjectMocks
    GameService gameService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateGame() {

        when(omdbClientExternalService.getRandomMovies()).thenReturn(MovieFixtures.createMoviesDto());
        when(playerService.getPlayer(anyString())).thenReturn(Optional.of(PlayerFixtures.createPlayer()));
        when(gameRepository.save(any())).thenReturn(GameFixtures.createGameOpen());
        when(gameRepository.findById(any())).thenReturn(Optional.of(GameFixtures.createGameOpen()));

         gameService.createGame(new CreateGameDto("Danilo"));
        List<String> movies = new ArrayList<String>();
        movies.add("Batman");
        movies.add("Superman");

        GameInfoDto expected = new GameInfoDto(movies, "1");

    }

    @Test
    void testJoinGame() {
//        when(omdbClientExternalService.getRandomMovies()).thenReturn(MovieFixtures.createMoviesDto());
        when(playerService.getPlayer(anyString())).thenReturn(Optional.of(PlayerFixtures.createPlayer()));
        when(gameRepository.findById(any())).thenReturn(Optional.of(GameFixtures.createGameReady()));
        when(gameRepository.save(any())).thenReturn(GameFixtures.createGameReady());
        when(playerGameDataRepository.save(PlayerGameDataFixtures.createPlayerGameData())).thenReturn(PlayerGameDataFixtures.createPlayerGameData());

         gameService.joinGame("1", new CreateJoinGameDto("Danilo"));
        then(gameRepository).should(times(1)).save(eq(GameFixtures.createGameReady()));
        then(playerGameDataRepository).should(times(1)).save(any());
       // then(gameService).shouldHaveNoMoreInteractions();
    }

    @Test
    void testPlay() {
        when(gameRepository.findById(any())).thenReturn(Optional.of(GameFixtures.createGameReady()));

        when(playerGameDataRepository.findByGame(any())).thenReturn(Optional.of(PlayerGameDataFixtures.createPlayerGameData()));

        GameInfoDto result = gameService.play("1", new CreatePlayerDto("Danilo", "Batman"));
    }

    @Test
    void testGetGame() {
        when(omdbClientExternalService.findMovieByTitle(anyString())).thenReturn(null);

        GameInfoDto result = gameService.getGame("gameId", "playerId");
        Assertions.assertEquals(new GameInfoDto(Arrays.<String>asList("String"), "gameId"), result);
    }
}

