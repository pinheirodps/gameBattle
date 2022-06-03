package com.challenge.moviesbattle.domain.game.fixtures;

import com.challenge.moviesbattle.domain.game.dto.GameOverDto;
import com.challenge.moviesbattle.domain.game.entities.Game;
import com.challenge.moviesbattle.domain.game.enums.GameStatus;

public class GameFixtures {


    public static Game createGameOpen(){
        Game game = new Game();
        game.setGameStatus(GameStatus.OPEN);
        game.setCurrentRound(1);
        game.setId(1l);
        game.setPlayer(PlayerFixtures.createPlayer());
        return game;
    }

    public static Game createGameOpenIntTests(){
        Game game = new Game();
        game.setGameStatus(GameStatus.OPEN);
        game.setCurrentRound(1);
        game.setFilled(Boolean.TRUE);
        return game;
    }

    public static Game createGameEnded() {
        Game game = new Game();
        game.setGameStatus(GameStatus.ENDED);
        game.setCurrentRound(1);
        game.setId(1l);
        game.setPlayer(PlayerFixtures.createPlayer());
        return game;
    }

    public static Game createGamePlaying(){
        Game game = new Game();
        game.setGameStatus(GameStatus.PLAYING);
        game.setCurrentRound(1);
        game.setId(1l);
        game.setPlayer(PlayerFixtures.createPlayer());
        return game;
    }

    public static GameOverDto createGameOverDto(){
        return GameOverDto.builder().gameId("1").gameStatus(GameStatus.ENDED).build();
    }


}
