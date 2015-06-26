package com.elgassia.bridge.Model;

public class UserTeamModel {
    private final TeamModel teamModel;
    private final int userID;

    public UserTeamModel(int userID, TeamModel teamModel) {
        this.userID = userID;
        this.teamModel = teamModel;
    }

    public UserLobbyModel getUserLobbyModel()
    {
        return new UserLobbyModel(userID, teamModel.getLobbyModel());
    }

    public UserBiddingModel getUserBiddingModel()
    {
        return new UserBiddingModel(userID, teamModel.getBiddingModel());
    }

    public boolean setTeam(int team) {
        return teamModel.setTeam(userID, team);
    }

    public int getUserTeam() {
        return teamModel.getUserTeam(userID);
    }

    public String getPlayerName() {
        return teamModel.getPlayerName(userID);
    }
}
