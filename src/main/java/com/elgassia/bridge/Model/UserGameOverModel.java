package com.elgassia.bridge.Model;

/**
 * Created by vereena on 6/27/15.
 */
public class UserGameOverModel {
    private int userID;
    private GameOverModel gameOverModel;
    UserGameOverModel(int userID,GameOverModel gameOverModel) {
        this.userID = userID;
        this.gameOverModel=gameOverModel;
    }
    int getGameWinner()
    {
        return gameOverModel.getGameWinner();
    }
    int getTurnsWonByThePlayingTeam()
    {
        return gameOverModel.getTurnsWonByThePlayingTeam();
    }
    Card[][] getPlayedCards()
    {
        return gameOverModel.getPlayedCards();
    }
    int[] getWhoStartedTurn()
    {
        return gameOverModel.getWhoStartedTurn();
    }
}
