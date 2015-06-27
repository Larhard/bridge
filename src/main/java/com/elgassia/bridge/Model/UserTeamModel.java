package com.elgassia.bridge.Model;

import java.util.Observable;
import java.util.Observer;

public class UserTeamModel extends Observable implements Observer{
    private final TeamModel teamModel;
    private final int userID;

    public UserTeamModel(int userID, TeamModel teamModel) {
        this.userID = userID;
        this.teamModel = teamModel;
        teamModel.addObserver(this);
    }

    public UserLobbyModel getUserLobbyModel()
    {
        return new UserLobbyModel(userID, teamModel.getLobbyModel());
    }

    public UserBiddingModel getUserBiddingModel()
    {
        return new UserBiddingModel(userID, teamModel.getBiddingModel());
    }

    public UserGameModel getUserGameModel() {
        return new UserGameModel(userID, teamModel.getGameModel());
    }

    public int getUserTeam() {
        return teamModel.getUserTeam(userID);
    }

    public String getPlayerName() {
        return teamModel.getPlayerName(userID);
    }

    @Override
    public void update(Observable o, Object arg) {
        setChanged();
        notifyObservers();
    }
}
