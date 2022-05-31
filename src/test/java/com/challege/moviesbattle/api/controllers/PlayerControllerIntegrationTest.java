package com.challege.moviesbattle.api.controllers;

import com.challege.moviesbattle.AbstractIntegrationTest;
import com.challege.moviesbattle.domain.jwt.entities.UserDao;
import com.challege.moviesbattle.domain.jwt.reporsitories.UserRepository;
import com.challege.moviesbattle.domain.player.dto.CreatePlayerDto;
import com.challege.moviesbattle.domain.player.dto.PlayerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PlayerControllerIntegrationTest extends AbstractIntegrationTest {

    private final static String CONTENT_TYPE = "application/json";
    private final static String CREATE_PLAYER_BASE_ROUTE = "/api/v1/player/";

    @LocalServerPort
    private int port;

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder bcryptEncoder;

    @Test
    void testCreatePlayer() {
        UserDao newUser = new UserDao();
        newUser.setUsername("danilo");
        newUser.setPassword(bcryptEncoder.encode("123"));
        userRepository.save(newUser);

        PlayerDto result = given().port(port).contentType(CONTENT_TYPE)
                .body(CreatePlayerDto.builder().username("danilo").build())
                .post(CREATE_PLAYER_BASE_ROUTE + "create")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .as(PlayerDto.class);
        assertThat(result).isEqualTo(PlayerDto.builder().userId("1").username("danilo").build());
    }
}

