package com.challege.moviesbattle.domain.game.fixtures;

import com.challege.moviesbattle.domain.player.dto.PlayerDto;
import com.challege.moviesbattle.domain.player.entities.Player;

import java.util.Set;

public class PlayerFixtures {

    public static Player createPlayer(){
        Player player = new Player();
        player.setUsername("Danilo");
        return player;
    }

    public static Player createPlayerWithGame() {
        Player player = new Player();
        player.setGames(Set.of(GameFixtures.createGameOpen()));
        player.setUsername("Danilo");
        return player;
    }

    public static PlayerDto createPlayerDto(){
        return PlayerDto.builder().userId("1").build();
    }

}
