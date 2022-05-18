package com.challege.moviesbattle.api.controllers;

import com.challege.moviesbattle.domain.player.dto.CreatePlayerDto;
import com.challege.moviesbattle.domain.player.dto.PlayerDto;
import com.challege.moviesbattle.domain.player.mapper.PlayerMapper;
import com.challege.moviesbattle.domain.player.services.PlayerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/player")

public class PlayerController {
    PlayerService playerService;

    @PostMapping(path = "/create")
    public ResponseEntity<PlayerDto> createPlayer(@RequestBody CreatePlayerDto player) {
        return ResponseEntity.ok(PlayerMapper.INSTANCE.from(this.playerService.createPlayer(player)));
    }
}
