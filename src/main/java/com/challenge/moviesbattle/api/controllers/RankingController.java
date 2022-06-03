package com.challenge.moviesbattle.api.controllers;

import com.challenge.moviesbattle.domain.ranking.dto.RankingResponseDto;
import com.challenge.moviesbattle.domain.ranking.services.RankingServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * The type Ranking controller.
 */
@RestController()
@AllArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api/v1/ranking")
public class RankingController {

    private RankingServices rankingServices;

    /**
     * Get ranking is calculated by score * count games of players.
     *
     * @return the response entity
     */
    @GetMapping
    @Operation(summary = "Get Ranking", security = @SecurityRequirement(name = "bearerAuth"), responses = {
            @ApiResponse(responseCode = "200", description = "Ranking founded", content = @Content(mediaType =
                    "application/json", schema = @Schema(implementation = RankingResponseDto.class)))})
    public ResponseEntity<List<RankingResponseDto>> getRanking(){
            return ResponseEntity.ok(rankingServices.getRanking());
    }
}
