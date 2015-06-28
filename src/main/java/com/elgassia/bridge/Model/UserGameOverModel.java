package com.elgassia.bridge.Model;

public class UserGameOverModel {
    private GameOverModel gameOverModel;
    UserGameOverModel(int userID,GameOverModel gameOverModel) {
        this.gameOverModel=gameOverModel;
    }
    public int getGameWinner()
    {
        return gameOverModel.getGameWinner();
    }
    public int getTurnsWonByThePlayingTeam()
    {
        return gameOverModel.getTurnsWonByThePlayingTeam();
    }
    public Card[][] getPlayedCards()
    {
        return gameOverModel.getPlayedCards();
    }
    public int[] getWhoStartedTurn()
    {
        return gameOverModel.getWhoStartedTurn();
    }
}
