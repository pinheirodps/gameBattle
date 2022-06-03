package com.challenge.moviesbattle.api.controllers;

import com.challenge.moviesbattle.domain.player.dto.CreatePlayerDto;
import com.challenge.moviesbattle.domain.player.dto.PlayerDto;
import com.challenge.moviesbattle.domain.player.services.PlayerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zalando.problem.Problem;

/**
 * The type Player controller.
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/player")
@SecurityRequirement(name = "bearerAuth")
public class PlayerController {
    private final PlayerService playerService;

    /**
     * Create player response entity.
     *
     * @param player the player
     * @return the response entity
     */
    @PostMapping(path = "/create")
    @Operation(summary = "Create a player", responses = {
            @ApiResponse(responseCode = "201", description = "Player created", content = @Content(mediaType =
                    "application/json", schema = @Schema(implementation = PlayerDto.class))),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content(schema =
            @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "400", description = "Username must not be null or empty", content =
            @Content(schema = @Schema(implementation = Problem.class)))})
    public ResponseEntity<PlayerDto> createPlayer(@RequestBody CreatePlayerDto player) {
        return new ResponseEntity<>(playerService.createPlayer(player), HttpStatus.CREATED);
    }
}
