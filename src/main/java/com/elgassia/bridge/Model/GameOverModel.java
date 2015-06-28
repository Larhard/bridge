package com.elgassia.bridge.Model;

import java.io.Serializable;

public class GameOverModel implements Serializable{
    private TeamModel teamModel;
    GameOverModel(TeamModel teamModel)
    {
        this.teamModel=teamModel;
    }
    public int getGameWinner()
    {
        return teamModel.getGameWinner();
    }
    public int getTurnsWonByThePlayingTeam()
    {
        return teamModel.getTurnsWonByThePlayingTeam();
    }
    public Card[][] getPlayedCards()
    {
        return teamModel.getPlayedCards();
    }
    public int[] getWhoStartedTurn()
    {
        return teamModel.getWhoStartedTurn();
    }
}
