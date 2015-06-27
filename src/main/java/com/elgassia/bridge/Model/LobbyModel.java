package com.elgassia.bridge.Model;

import com.elgassia.bridge.exception.BridgeLogicException;

import java.io.Serializable;
import java.util.Observable;
import java.util.Random;

/**
 * Created by vereena on 6/19/15.
 */
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
        if(readyUsers[user]==false) {
            teamModel.setName(user, name);
            setChanged();
            notifyObservers();
            return true;
        }
        throw new BridgeLogicException("You can't change your name when you said you're ready");
    }
    boolean setTeam(int user,int team) throws BridgeLogicException {
        if(readyUsers[user]==false) {
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
        if(readyUsers[user]==false) {
            int team = new Random().nextInt(2);
            try {
                while (!setTeam(user, team)) {
                    team = new Random().nextInt(2);
                }
            } catch (BridgeLogicException e){
                throw new Error(e.getCause());
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
            if(readyUsers[i]==false) {
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
}
