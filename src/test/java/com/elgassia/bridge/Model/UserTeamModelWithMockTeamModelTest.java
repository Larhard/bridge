package com.elgassia.bridge.Model;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class UserTeamModelWithMockTeamModelTest {

    TeamModel teamModel;
    UserTeamModel userTeamModel;
    @Before
    public void setUp()
    {
        teamModel=mock(TeamModel.class);
        userTeamModel=new UserTeamModel(0,teamModel);
    }
    @Test
    public void testGetUserTeam() throws Exception {
        userTeamModel.getUserTeam();
        verify(teamModel,times(1)).getUserTeam(0);
    }

    @Test
    public void testGetPlayerName() throws Exception {
        userTeamModel.getPlayerName();
        verify(teamModel,times(1)).getPlayerName(0);
    }
}