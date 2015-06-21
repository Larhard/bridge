package com.elgassia.bridge.Model;

public class UserTeamModel {
    private final TeamModel teamModel;
    private final int userID;

    public UserTeamModel(int userID, TeamModel teamModel) {
        this.userID = userID;
        this.teamModel = teamModel;
    }

    UserLobbyModel getUserLobbyModel()
    {
        return new UserLobbyModel(userID, teamModel.getLobbyModel());
    }

    UserBiddingModel getUserBiddingModel()
    {
        return new UserBiddingModel(userID, teamModel.getBiddingModel());
    }

    boolean setTeam(int team) {
        return teamModel.setTeam(userID, team);
    }

    int getUserTeam() {
        return teamModel.getUserTeam(userID);
    }

    String getPlayerName() {
        return teamModel.getPlayerName(userID);
    }
}
