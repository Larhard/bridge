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
        return new LobbyModel(this);
    }
    BiddingModel getBiddingModel()
    {
        return new BiddingModel(this);
    }
    GameModel getGameModel()
    {
        return new GameModel(this);
    }
    GameOverModel getGameOverModel()
    {
        return new GameOverModel(this);
    }
    void changeGameState(char state)
    {
        this.state=state;
        setChanged();
        notifyObservers();
    }
    void setName(int user,String name)
    {
        players[user]=name;
        setChanged();
        notifyObservers();
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
    int getUserTeam(int user)
    {
        return playerTeam[user];
    }
}
