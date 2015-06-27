package com.elgassia.bridge.Model;

import com.elgassia.bridge.exception.BridgeLogicException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LobbyTest {
    TeamModel teamModel;
    UserTeamModel userTeamModel0;
    UserTeamModel userTeamModel1;
    UserTeamModel userTeamModel2;
    UserTeamModel userTeamModel3;

    @Before
    public void setUp() throws Exception {
        teamModel = new TeamModel();

        userTeamModel0 = new UserTeamModel(0, teamModel);
        userTeamModel1 = new UserTeamModel(1, teamModel);
        userTeamModel2 = new UserTeamModel(2, teamModel);
        userTeamModel3 = new UserTeamModel(3, teamModel);
    }

    @Test
    public void testSetTeamRange () throws BridgeLogicException {
        userTeamModel0.getUserLobbyModel().setTeam(0);
        userTeamModel0.getUserLobbyModel().setTeam(1);

        boolean exception;

        exception = false;
        try {
            userTeamModel0.getUserLobbyModel().setTeam(-1);
        } catch (BridgeLogicException e) {
            exception = true;
        }
        assertTrue(exception);

        exception = false;
        try {
            userTeamModel0.getUserLobbyModel().setTeam(2);
        } catch (BridgeLogicException e) {
            exception = true;
        }
        assertTrue(exception);

        exception = false;
        try {
            userTeamModel0.getUserLobbyModel().setTeam(123);
        } catch (BridgeLogicException e) {
            exception = true;
        }
        assertTrue(exception);
    }
}
