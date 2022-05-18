package com.challege.moviesbattle.domain.game.fixtures;

import com.challege.moviesbattle.domain.game.entities.PlayerGameData;

public class PlayerGameDataFixtures {

    public static PlayerGameData createPlayerGameData(){
        PlayerGameData playerGameData = new PlayerGameData();
        playerGameData.setGame(GameFixtures.createGameReady());
        playerGameData.setPlayer(PlayerFixtures.createPlayerWithGame());
        playerGameData.setScore(0);
        return playerGameData;
    }

}
