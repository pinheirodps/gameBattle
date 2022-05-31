package com.challege.moviesbattle.api.controllers;

import com.challege.moviesbattle.AbstractIntegrationTest;
import com.challege.moviesbattle.domain.game.dto.CreateGameDto;
import com.challege.moviesbattle.domain.game.dto.GameInfoDto;
import com.challege.moviesbattle.domain.game.dto.GameOverDto;
import com.challege.moviesbattle.domain.game.dto.GameResponseDto;
import com.challege.moviesbattle.domain.game.entities.Game;
import com.challege.moviesbattle.domain.game.entities.Movie;
import com.challege.moviesbattle.domain.game.enums.GameStatus;
import com.challege.moviesbattle.domain.game.fixtures.GameFixtures;
import com.challege.moviesbattle.domain.game.fixtures.MovieFixtures;
import com.challege.moviesbattle.domain.game.fixtures.PlayerFixtures;
import com.challege.moviesbattle.domain.game.repositories.GameRepository;
import com.challege.moviesbattle.domain.movie.repositories.MovieRepository;
import com.challege.moviesbattle.domain.movie.services.MovieService;
import com.challege.moviesbattle.domain.player.dto.PlayDto;
import com.challege.moviesbattle.domain.player.entities.Player;
import com.challege.moviesbattle.domain.player.repositories.PlayerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class GameControllerIntegrationTest extends AbstractIntegrationTest {

    private final static String CONTENT_TYPE = "application/json";
    private final static String CREATE_GAME_BASE_ROUTE = "/api/v1/game/";
    public static final int GAME_ID = 1;
    @LocalServerPort
    private int port;
    @Autowired
    PlayerRepository playerRepository;
    @Autowired
    GameRepository gameRepository;
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    MovieService movieService;


    @Test
    public void createGameExistingPlayer() {

       playerRepository.save(PlayerFixtures.createPlayer());

        GameResponseDto result = given().port(port).contentType(CONTENT_TYPE)
                .body(CreateGameDto.builder().username("Danilo").build())
                .post(CREATE_GAME_BASE_ROUTE+ "create")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .as(GameResponseDto.class);
        assertThat(result).isEqualTo(GameResponseDto.builder().gameId("1").gameStatus(GameStatus.OPEN).build());

    }

    @Test
    void testGetGame() {
        Player player = playerRepository.save(PlayerFixtures.createPlayer());
        Game game = GameFixtures.createGameOpenIntTests();
        game.setPlayer(player);
        gameRepository.save(game);
        movieRepository.saveAll(MovieFixtures.createMoviesDao());

        GameInfoDto result = given().port(port).contentType(CONTENT_TYPE)
                .get(CREATE_GAME_BASE_ROUTE+ GAME_ID)
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .as(GameInfoDto.class);
        assertThat(result).isEqualTo(GameInfoDto.builder().gameId("1").movies(new HashSet<>(MovieFixtures.createMovies()))
                .build());
    }

    @Test
    void testPlay() {
        Player player = playerRepository.save(PlayerFixtures.createPlayer());
        Game game = GameFixtures.createGameOpenIntTests();
        game.setPlayer(player);
        gameRepository.save(game);
        movieRepository.saveAll(MovieFixtures.createMoviesDao());

        GameInfoDto result = given().port(port).contentType(CONTENT_TYPE)
                .body(PlayDto.builder().movieTitle("Batman").username("Danilo").build())
                .patch(CREATE_GAME_BASE_ROUTE+ GAME_ID+"/play")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .as(GameInfoDto.class);
        List<Movie> movies = movieRepository.findAll();
        assertThat(result).isEqualTo(GameInfoDto.builder().gameId("1")
                .movies(movies.stream().map(Movie::getTitle)
                        .collect(Collectors.toSet())).build());
        assertThat(playerRepository.findByUsername("Danilo").get().getScore()).isEqualTo(1);
    }

    @Test
    void testGameOver() {
        Player player = playerRepository.save(PlayerFixtures.createPlayer());
        Game game = GameFixtures.createGameOpenIntTests();
        game.setPlayer(player);
        gameRepository.save(game);

        GameOverDto result = given().port(port).contentType(CONTENT_TYPE)
                .body(CreateGameDto.builder().username("Danilo").build())
                .post(CREATE_GAME_BASE_ROUTE+ "gameOver/"+GAME_ID)
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .as(GameOverDto.class);
        assertThat(result).isEqualTo(GameOverDto.builder().gameId("1").gameStatus(GameStatus.ENDED).build());
    }

}

