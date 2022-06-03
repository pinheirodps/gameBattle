package com.challenge.moviesbattle.api.controllers;

import com.challenge.moviesbattle.AbstractIntegrationTest;
import com.challenge.moviesbattle.config.RunApiExternal;
import com.challenge.moviesbattle.domain.game.dto.CreateGameDto;
import com.challenge.moviesbattle.domain.game.dto.GameInfoDto;
import com.challenge.moviesbattle.domain.game.dto.GameOverDto;
import com.challenge.moviesbattle.domain.game.dto.GameResponseDto;
import com.challenge.moviesbattle.domain.game.entities.Game;
import com.challenge.moviesbattle.domain.game.entities.Movie;
import com.challenge.moviesbattle.domain.game.enums.GameStatus;
import com.challenge.moviesbattle.domain.game.fixtures.GameFixtures;
import com.challenge.moviesbattle.domain.game.fixtures.MovieFixtures;
import com.challenge.moviesbattle.domain.game.fixtures.PlayerFixtures;
import com.challenge.moviesbattle.domain.game.repositories.GameRepository;
import com.challenge.moviesbattle.domain.movie.repositories.MovieRepository;
import com.challenge.moviesbattle.domain.movie.services.MovieService;
import com.challenge.moviesbattle.domain.player.dto.PlayDto;
import com.challenge.moviesbattle.domain.player.entities.Player;
import com.challenge.moviesbattle.domain.player.repositories.PlayerRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Slf4j
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

    @BeforeAll
    public static void setup() throws InterruptedException {
        log.info("startup api...");
        RunApiExternal.run();
        Thread.sleep(800);
    }

    @AfterAll
    public static void tearDown() throws InterruptedException {
        log.info("stop api...");
        Thread.sleep(200);
        RunApiExternal.stop();
    }

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

