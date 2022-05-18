package com.challege.moviesbattle.api.controllers;

import com.challege.moviesbattle.domain.game.dto.CreateGameDto;
import com.challege.moviesbattle.domain.game.dto.GameDto;
import com.challege.moviesbattle.domain.game.dto.GameInfoDto;
import com.challege.moviesbattle.domain.game.services.GameService;
import com.challege.moviesbattle.domain.player.dto.CreateJoinGameDto;
import com.challege.moviesbattle.domain.player.dto.CreatePlayerDto;
import com.challege.moviesbattle.domain.player.entities.Player;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/game")
public class GameController {
    public static final String CONTENT_LENGTH = "Content-Length";

    private final GameService gameService;

    @PostMapping("/create")
    public ResponseEntity<GameInfoDto> create(@RequestBody final CreateGameDto createGame) {
        log.info("create game request: {}", createGame);
        gameService.createGame(createGame);
        return ResponseEntity.accepted().build();
    }

    @GetMapping(path = "/{gameId}/player/{playerId}")
    public ResponseEntity<GameInfoDto> getGame(@PathVariable final String gameId,
                                               @PathVariable final String playerId) {
        return ResponseEntity.ok(gameService.getGame(gameId, playerId));
    }

    @PatchMapping(path = "/{gameId}/play")
    public ResponseEntity<GameInfoDto> play(@PathVariable final String gameId,
                                            @RequestBody final CreatePlayerDto playerDto) {
        return ResponseEntity.ok(gameService.play(gameId, playerDto));
    }


    @PatchMapping(path = "/join/{gameId}")
    public ResponseEntity<Void> joinGame(@PathVariable final String gameId,
                                         @RequestBody final CreateJoinGameDto createJoinGameDto) {
        gameService.joinGame(gameId, createJoinGameDto);

        return ResponseEntity.noContent().header(CONTENT_LENGTH, "0").build();
    }

}
