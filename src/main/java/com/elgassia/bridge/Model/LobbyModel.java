package com.elgassia.bridge.Model;

import com.elgassia.bridge.exception.BridgeLogicException;

import java.io.Serializable;
import java.util.Observable;
import java.util.Random;

public class LobbyModel extends Observable implements Serializable{
    private TeamModel teamModel;
    private boolean readyUsers[]=new boolean[4];
    LobbyModel(TeamModel teamModel)
    {
        for(int i=0;i<4;i++)
            readyUsers[i]=false;
        this.teamModel=teamModel;
    }
    boolean setName(int user,String name) throws BridgeLogicException {
        if(!readyUsers[user]) {
            teamModel.setName(user, name);
            setChanged();
            notifyObservers();
            return true;
        }
        throw new BridgeLogicException("You can't change your name when you said you're ready");
    }
    boolean setTeam(int user,int team) throws BridgeLogicException {
        if(!readyUsers[user]) {
            if(teamModel.setTeam(user, team))
            {
                setChanged();
                notifyObservers();
                return true;
            }
            return false;
        }
        throw new BridgeLogicException("You can't change your team when you said you're ready");
    }
    boolean randomTeam(int user) throws BridgeLogicException {
        if(!readyUsers[user]) {
            while (true)
            {
                int team = new Random().nextInt(2);
                try {
                    setTeam(user,team);
                }catch (BridgeLogicException e)
                {
                    continue;
                }
                break;
            }
            setChanged();
            notifyObservers();
            return true;
        }
        throw new BridgeLogicException("You can't change your team when you said you're ready");
    }
    boolean ready(int user) throws BridgeLogicException {
        if (teamModel.getUserTeam(user)==-1)
            throw new BridgeLogicException("You must set your team before you say you're ready");
        readyUsers[user]=true;
        for(int i=0;i<4;i++)
        {
            if(!readyUsers[i]) {
                setChanged();
                notifyObservers();
                return true;
            }
        }
        teamModel.drawCards();
        teamModel.changeGameState('B');
        setChanged();
        notifyObservers();
        return true;
    }
    void chooseDeckStrategy(Strategy strategy)
    {
        teamModel.chooseDeckStrategy(strategy);
        setChanged();
        notifyObservers();
    }

    public String getName(int userID) {
        return teamModel.getPlayerName(userID);
    }

    public void resetPlayerStates() {
        for (int i = 0; i < readyUsers.length; ++i) {
            readyUsers[i] = false;
        }
    }
}
