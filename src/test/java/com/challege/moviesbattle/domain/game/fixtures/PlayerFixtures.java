package com.challege.moviesbattle.domain.game.fixtures;

import com.challege.moviesbattle.domain.player.entities.Player;

public class PlayerFixtures {

    public static Player createPlayer(){
        Player player = new Player();
        player.setUsername("Danilo");
        return player;
    }

    public static Player createPlayerWithGame() {
        Player player = new Player();
        player.setGame(GameFixtures.createGameOpen());
        player.setUsername("Danilo");
        return player;
    }

}
