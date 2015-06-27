package com.elgassia.bridge.Model;

import java.io.Serializable;

/**
 * Created by vereena on 6/19/15.
 */
public class GameOverModel implements Serializable{
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
