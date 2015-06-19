package com.elgassia.bridge.Model;

/**
 * Created by vereena on 6/19/15.
 */
public class LobbyModel {
    TeamModel teamModel;
    LobbyModel(TeamModel teamModel)
    {
        this.teamModel=teamModel;
    }
    boolean setTeam(int user,int team)
    {
        return teamModel.setTeam(user,team);
    }
}
