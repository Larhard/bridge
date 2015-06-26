package com.elgassia.bridge.Model;

import com.elgassia.bridge.exception.BridgeLogicException;

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
    public boolean setName(String name)
    {
        return lobbyModel.setName(userID,name);
    }
    public boolean setTeam(int team) throws BridgeLogicException {
        return lobbyModel.setTeam(userID,team);
    }
    public boolean randomTeam(){
        return lobbyModel.randomTeam(userID);
    }
    public boolean ready()
    {
        return lobbyModel.ready(userID);
    }
    void chooseDeckStrategy(Strategy strategy)
    {
        lobbyModel.chooseDeckStrategy(strategy);
    }
}
