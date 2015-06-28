package com.elgassia.bridge.Model;

import com.elgassia.bridge.exception.BridgeLogicException;

import java.util.Observable;
import java.util.Observer;

public class UserLobbyModel extends Observable implements Observer{
    private int userID;
    private LobbyModel lobbyModel;
    UserLobbyModel(int userID,LobbyModel lobbyModel) {
        this.userID = userID;
        this.lobbyModel=lobbyModel;
    }
    public boolean setName(String name) throws BridgeLogicException {
        if(lobbyModel.setName(userID,name))
        {
            setChanged();
            notifyObservers();
            return true;
        }
        return false;
    }
    public boolean setTeam(int team) throws BridgeLogicException {
        if(lobbyModel.setTeam(userID,team))
        {
            setChanged();
            notifyObservers();
            return true;
        }
        return false;
    }
    public boolean randomTeam() throws BridgeLogicException {
        if(lobbyModel.randomTeam(userID))
        {
            setChanged();
            notifyObservers();
            return true;
        }
        return false;
    }
    public boolean ready() throws BridgeLogicException {
        if(lobbyModel.ready(userID))
        {
            setChanged();
            notifyObservers();
            return true;
        }
        return false;
    }
    void chooseDeckStrategy(Strategy strategy)
    {
        lobbyModel.chooseDeckStrategy(strategy);
        setChanged();
        notifyObservers();
    }

    @Override
    public void update(Observable o, Object arg) {
        setChanged();
        notifyObservers();
    }
}
