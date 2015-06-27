package com.elgassia.bridge.Model;

/**
 * Created by vereena on 6/19/15.
 */
public class GameOverModel {
    private TeamModel teamModel;
    GameOverModel(TeamModel teamModel)
    {
        this.teamModel=teamModel;
    }
    int getGameWinner()
    {
        return teamModel.getGameWinner();
    }
    int getTurnsWonByThePlayingTeam()
    {
        return teamModel.getTurnsWonByThePlayingTeam();
    }
    Card[][] getPlayedCards()
    {
        return teamModel.getPlayedCards();
    }
    int[] getWhoStartedTurn()
    {
        return teamModel.getWhoStartedTurn();
    }
}
