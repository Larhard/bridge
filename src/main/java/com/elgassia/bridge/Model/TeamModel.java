package com.elgassia.bridge.Model;

import java.util.Observable;

/**
 * Created by vereena on 6/19/15.
 */
public class TeamModel extends Observable{
    char state;
    String players[]=new String[4];
    int playerTeam[]=new int[4];
    TeamModel()
    {
        players[0]="user1";
        players[1]="user2";
        players[2]="user3";
        players[3]="user4";
        for(int i=0;i<4;i++)
            playerTeam[i]=-1;
        state='0';
    }
    LobbyModel getLobbyModel()
    {
        if(state=='0') {
            state='L';
            return new LobbyModel(this);
        }
        return null;
    }
    BiddingModel getBiddingModel()
    {
        if(state=='L') {
            state='B';
            return new BiddingModel(this);
        }
        return null;
    }
    GameModel getGameModel()
    {
        if(state=='B') {
            state='G';
            return new GameModel(this);
        }
        return null;
    }
    GameOverModel getGameOverModel()
    {
        if(state=='G') {
            state='O';
            return new GameOverModel(this);
        }
        return null;
    }
    boolean setName(int user,String name)
    {
        players[user]=name;
        setChanged();
        notifyObservers();
        return true;
    }
    private int countTeamPlayers(int team)
    {
        int counter=0;
        for (int i=0;i<4;i++)
        {
            if (playerTeam[i]==team)
                counter++;
        }
        return counter;
    }
    boolean setTeam(int user,int team)
    {
        if(playerTeam[user]==team)
            return true;
        if(countTeamPlayers(team)<2)
        {
            playerTeam[user]=team;
            setChanged();
            notifyObservers();
            return true;
        }
        return false;
    }
}
