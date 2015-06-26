package com.elgassia.bridge.Model;

import com.elgassia.bridge.exception.BridgeLogicException;

import java.util.Random;

/**
 * Created by vereena on 6/19/15.
 */
public class LobbyModel {
    private TeamModel teamModel;
    private boolean readyUsers[]=new boolean[4];
    LobbyModel(TeamModel teamModel)
    {
        for(int i=0;i<4;i++)
            readyUsers[i]=false;
        this.teamModel=teamModel;
    }
    boolean setName(int user,String name)
    {
        if(readyUsers[user]==false) {
            teamModel.setName(user, name);
            return true;
        }
        return false;
    }
    boolean setTeam(int user,int team) throws BridgeLogicException {
        if(readyUsers[user]==false) {
            return teamModel.setTeam(user, team);
        }
        return false;
    }
    boolean randomTeam(int user) {
        if(readyUsers[user]==false) {
            int team = new Random().nextInt(2);
            try {
                while (!setTeam(user, team)) {
                    team = new Random().nextInt(2);
                }
            } catch (BridgeLogicException e){
                throw new Error(e.getCause());
            }
            return true;
        }
        return false;
    }
    boolean ready(int user)
    {
        if (teamModel.getUserTeam(user)==-1)
            return false;
        readyUsers[user]=true;
        for(int i=0;i<4;i++)
        {
            if(readyUsers[i]==false)
                return true;
        }
        teamModel.drawCards();
        teamModel.changeGameState('B');
        return true;
    }
    void chooseDeckStrategy(Strategy strategy)
    {
        teamModel.chooseDeckStrategy(strategy);
    }
}
