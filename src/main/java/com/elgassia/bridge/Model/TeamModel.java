package com.elgassia.bridge.Model;

/**
 * Created by vereena on 6/19/15.
 */
public class TeamModel {
    char state;
    TeamModel()
    {
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
}
