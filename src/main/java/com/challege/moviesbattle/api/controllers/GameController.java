package com.challege.moviesbattle.api.controllers;

import com.challege.moviesbattle.domain.game.dto.CreateGameDto;
import com.challege.moviesbattle.domain.game.dto.GameInfoDto;
import com.challege.moviesbattle.domain.game.dto.GameOverDto;
import com.challege.moviesbattle.domain.game.dto.GameResponseDto;
import com.challege.moviesbattle.domain.game.services.GameService;
import com.challege.moviesbattle.domain.player.dto.PlayDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zalando.problem.Problem;

/**
 * The type Game controller.
 */
@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/api/v1/game")
@SecurityRequirement(name = "bearerAuth")
public class GameController {
    private final GameService gameService;

    /**
     * Create response entity.
     *
     * @param createGame the creation game.
     * @return the response gameId and gameStatus.
     */
    @PostMapping("/create")
    @Operation(summary = "Create a game", responses = {
            @ApiResponse(responseCode = "201", description = "Game created", content = @Content(mediaType =
                    "application/json", schema = @Schema(implementation = GameResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Player not found", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    public ResponseEntity<GameResponseDto> create(@RequestBody final CreateGameDto createGame) {
        log.info("create game request: {}", createGame);
        return new ResponseEntity<>(gameService.createGame(createGame), HttpStatus.CREATED);
    }

    /**
     * Gets game and with movies.
     *
     * @param gameId the game id
     * @return the gameId and movies
     */
    @GetMapping(path = "/{gameId}")
    @Operation(summary = "Get a game", responses = {
            @ApiResponse(responseCode = "200", description = "Game founded", content = @Content(mediaType =
                    "application/json", schema = @Schema(implementation = GameInfoDto.class))),
            @ApiResponse(responseCode = "404", description = "Game not found", content = @Content(schema =
            @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "409", description = "Game over: Game was finished",
                    content = @Content(schema =
                    @Schema(implementation = Problem.class)))})
    public ResponseEntity<GameInfoDto> getGame( @PathVariable final Long gameId) {
        return ResponseEntity.ok(gameService.getGame(gameId));
    }

    /**
     * Play response gameId and movies to continue playing.
     *
     * @param gameId  the game id
     * @param playDto the play dto
     * @return the response entity
     */
    @PatchMapping(path = "/{gameId}/play")
    @Operation(summary = "Play a game", responses = {
            @ApiResponse(responseCode = "200", description = "Playing a game", content = @Content(mediaType =
                    "application/json", schema = @Schema(implementation = GameInfoDto.class))),
            @ApiResponse(responseCode = "404", description = "Player not found, Game not found, Movie not found",
                    content = @Content(schema = @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "409", description = "Game over: Game was finished",
                    content = @Content(schema =
                    @Schema(implementation = Problem.class)))})
    public ResponseEntity<GameInfoDto> play(@PathVariable final Long gameId,
                                            @RequestBody final PlayDto playDto) {
        return ResponseEntity.ok(gameService.play(gameId, playDto));
    }

    /**
     * Game over response gameId and gameStatus ENDED.
     *
     * @param gameId the game id
     * @return the response entity
     */
    @PostMapping("/gameOver/{gameId}")
    @Operation(summary = "Game Over", responses = {
            @ApiResponse(responseCode = "200", description = "Game over, game was ended.", content = @Content(mediaType =
                    "application/json", schema = @Schema(implementation = GameOverDto.class))),
            @ApiResponse(responseCode = "404", description = "Game not found", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    public ResponseEntity<GameOverDto> gameOver(@PathVariable final Long gameId) {
        log.info("Game over : {}", gameId);
        return ResponseEntity.ok(gameService.gameOver(gameId));
    }

}
