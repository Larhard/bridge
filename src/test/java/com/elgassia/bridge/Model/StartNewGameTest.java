package com.elgassia.bridge.Model;

import junit.framework.TestCase;

public class StartNewGameTest extends TestCase {
    public void testReadyOnUserLobbyModels() throws Exception {
        TeamModel teamModel = new TeamModel();

        assertEquals("Starting with Lobby state", 'L', teamModel.getState());

        UserTeamModel userTeamModel0 = new UserTeamModel(0, teamModel);
        UserTeamModel userTeamModel1 = new UserTeamModel(1, teamModel);
        UserTeamModel userTeamModel2 = new UserTeamModel(2, teamModel);
        UserTeamModel userTeamModel3 = new UserTeamModel(3, teamModel);

        userTeamModel0.getUserLobbyModel().randomTeam();
        userTeamModel1.getUserLobbyModel().randomTeam();
        userTeamModel2.getUserLobbyModel().randomTeam();
        userTeamModel3.getUserLobbyModel().randomTeam();

        userTeamModel0.getUserLobbyModel().ready();
        userTeamModel1.getUserLobbyModel().ready();
        userTeamModel2.getUserLobbyModel().ready();
        userTeamModel3.getUserLobbyModel().ready();

        assertEquals("Ending with Bidding state", 'B', teamModel.getState());
    }
}