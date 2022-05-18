package com.challege.moviesbattle.domain.game.fixtures;

import com.challege.moviesbattle.domain.game.entities.Game;
import com.challege.moviesbattle.domain.game.entities.Movie;
import com.challege.moviesbattle.domain.game.enums.GameStatus;

public class GameFixtures {


    public static Game createGameOpen(){
        Game game = new Game();
        game.setGameStatus(GameStatus.OPEN);
        game.setMovies(MovieFixtures.createMovies());
        game.setCurrentRound(1);
        game.setId("1");
        return game;
    }

    public static Game createGameReady() {
        Game game = new Game();
        game.setGameStatus(GameStatus.READY);
        game.setMovies(MovieFixtures.createMovies());
        game.setCurrentRound(1);
        game.setId("1");
        game.setPlayer(PlayerFixtures.createPlayer());
        return game;
    }


}
