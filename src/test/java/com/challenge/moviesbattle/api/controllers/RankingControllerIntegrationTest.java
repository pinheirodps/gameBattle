package com.challenge.moviesbattle.api.controllers;

import com.challenge.moviesbattle.AbstractIntegrationTest;
import com.challenge.moviesbattle.domain.game.entities.Game;
import com.challenge.moviesbattle.domain.game.fixtures.GameFixtures;
import com.challenge.moviesbattle.domain.game.fixtures.PlayerFixtures;
import com.challenge.moviesbattle.domain.game.repositories.GameRepository;
import com.challenge.moviesbattle.domain.player.entities.Player;
import com.challenge.moviesbattle.domain.player.repositories.PlayerRepository;
import com.challenge.moviesbattle.domain.ranking.dto.RankingResponseDto;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RankingControllerIntegrationTest extends AbstractIntegrationTest {

    private final static String CONTENT_TYPE = "application/json";
    private final static String CREATE_RANKING_BASE_ROUTE = "/api/v1/ranking";
    @LocalServerPort
    private int port;
    @Autowired
    PlayerRepository playerRepository;
    @Autowired
    GameRepository gameRepository;

    @Test
    public void testGetRanking()  {
        Player danilo = PlayerFixtures.createPlayer();
        danilo.setScore(4);
        Player lila = PlayerFixtures.createPlayer();
        lila.setUsername("lila");
        lila.setScore(2);

        List<Player> players = new ArrayList<>();
        players.add(lila);
        players.add(danilo);
        playerRepository.saveAll(players);

        Game gameDanilo = GameFixtures.createGameOpenIntTests();
        Game gameDanilo2 = GameFixtures.createGameOpenIntTests();
        gameDanilo.setPlayer(danilo);
        gameDanilo2.setPlayer(danilo);
        gameDanilo2.getPlayer().setScore(gameDanilo2.getPlayer().getScore()+1);
        Game gameLila = GameFixtures.createGameOpenIntTests();
        gameLila.setPlayer(lila);

        List<Game> games = new ArrayList<>();
        games.add(gameLila);
        games.add(gameDanilo);
        games.add(gameDanilo2);
        gameRepository.saveAll(games);

        List<RankingResponseDto> result = given().port(port).contentType(CONTENT_TYPE)
                .get(CREATE_RANKING_BASE_ROUTE)
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .body()
                .jsonPath()
                .getList(".", RankingResponseDto.class);


        List<RankingResponseDto> ranking = new ArrayList<>();
        ranking.add(RankingResponseDto.builder().name("Danilo").score(8l).build());
        ranking.add(RankingResponseDto.builder().name("lila").score(2l).build());
        assertNotNull(result);
        assertThat(result).isEqualTo(ranking);
    }
}

