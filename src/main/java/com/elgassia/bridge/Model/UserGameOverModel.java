package com.elgassia.bridge.Model;

public class UserGameOverModel {
    private GameOverModel gameOverModel;
    UserGameOverModel(int userID,GameOverModel gameOverModel) {
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
