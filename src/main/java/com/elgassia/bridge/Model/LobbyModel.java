package com.elgassia.bridge.Model;

import java.util.Random;

/**
 * Created by vereena on 6/19/15.
 */
public class LobbyModel {
    TeamModel teamModel;
    LobbyModel(TeamModel teamModel)
    {
        this.teamModel=teamModel;
    }
    boolean setName(int user,String name)
    {
        return teamModel.setName(user,name);
    }
    boolean setTeam(int user,int team)
    {
        return teamModel.setTeam(user,team);
    }
    boolean randomTeam(int user)
    {
        int team=new Random().nextInt(2);
        while (!setTeam(user,team))
        {
            team=new Random().nextInt(2);
        }
        return true;
    }
}
