package com.elgassia.bridge.Model;

/**
 * Created by vereena on 6/19/15.
 */
public class UserLobbyModel {
    private int userID;
    private LobbyModel lobbyModel;
    UserLobbyModel(int userID,LobbyModel lobbyModel) {
        this.userID = userID;
        this.lobbyModel=lobbyModel;
    }
    boolean setName(String name)
    {
        return lobbyModel.setName(userID,name);
    }
    boolean setTeam(int team)
    {
        return lobbyModel.setTeam(userID,team);
    }
    boolean randomTeam()
    {
        return lobbyModel.randomTeam(userID);
    }
    boolean ready()
    {
        return lobbyModel.ready(userID);
    }
}
