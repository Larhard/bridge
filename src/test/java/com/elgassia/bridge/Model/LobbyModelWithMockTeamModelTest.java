package com.elgassia.bridge.Model;

import com.elgassia.bridge.exception.BridgeLogicException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class LobbyModelWithMockTeamModelTest {

    TeamModel teamModel;
    LobbyModel lobbyModel;
    @Before
    public void setUp()
    {
        teamModel=mock(TeamModel.class);
        lobbyModel=new LobbyModel(teamModel);
    }

    @Test
    public void testSetNameWhenReady() throws Exception {
        lobbyModel.ready(0);
        boolean y=false;
        try {
            lobbyModel.setName(0,"Ala");
        }
        catch (BridgeLogicException e)
        {
            y=true;
        }
        assertTrue("setName when user is ready didn't throw an exception",y);
    }

    @Test
    public void testSetNameWhenNotReady() throws Exception {
        assertTrue("setName failed",lobbyModel.setName(0,"Ala"));
        verify(teamModel,times(1)).setName(0,"Ala");
    }

    @Test
    public void testSetTeamWhenReady() throws Exception {
        lobbyModel.ready(0);
        boolean y=false;
        try {
            lobbyModel.setTeam(0, 0);
        }
        catch (BridgeLogicException e)
        {
            y=true;
        }
        assertTrue("setTeam when user is ready didn't throw an exception",y);
    }

    @Test
    public void testSetTeamWhenNotReady() throws Exception {
        when(teamModel.setTeam(anyInt(),anyInt())).thenReturn(true).thenThrow(new BridgeLogicException());
        assertTrue("setTeam failed",lobbyModel.setTeam(0,1));
        verify(teamModel,times(1)).setTeam(0,1);
        boolean y=false;
        try {
            lobbyModel.setTeam(0, -4);
        }
        catch (BridgeLogicException e)
        {
            y=true;
        }
        assertTrue("setTeam didn't throw an exception when teamModel threw one",y);
        verify(teamModel,times(1)).setTeam(0,-4);
    }

    @Test
    public void testRandomTeam() throws Exception {
        assertTrue("randomTeam failed",lobbyModel.randomTeam(0));
        verify(teamModel).setTeam(anyInt(),anyInt());
        lobbyModel.ready(0);
        boolean y=false;
        try {
            lobbyModel.randomTeam(0);
        }
        catch (BridgeLogicException e)
        {
            y=true;
        }
        assertTrue("randomTeam didn't throw an exception when ready",y);
    }

    @Test
    public void testReadyIfTeamIsNotSet() throws Exception {
        when(teamModel.getUserTeam(0)).thenReturn(-1);
        boolean y=false;
        try {
            lobbyModel.ready(0);
        }
        catch (BridgeLogicException e)
        {
            y=true;
        }
        assertTrue("ready didn't throw an exception when team is not set",y);
        verify(teamModel,times(1)).getUserTeam(0);
    }

    @Test
    public void testReadyIfTeamIsSet() throws Exception {
        when(teamModel.getUserTeam(0)).thenReturn(1);
        assertTrue("ready failed",lobbyModel.ready(0));
        verify(teamModel,times(1)).getUserTeam(0);
    }

    @Test
    public void testFourReadysIfTeamIsSet() throws Exception {
        when(teamModel.getUserTeam(anyInt())).thenReturn(1).thenReturn(1).thenReturn(0).thenReturn(0);
        lobbyModel.ready(0);
        lobbyModel.ready(1);
        lobbyModel.ready(2);
        lobbyModel.ready(3);
        verify(teamModel, times(4)).getUserTeam(anyInt());
        verify(teamModel,times(1)).changeGameState('B');
        verify(teamModel,times(1)).drawCards();
    }

    @Test
    public void testChooseDeckStrategy() throws Exception {
        lobbyModel.chooseDeckStrategy(new RandomStrategy());
        verify(teamModel,times(1)).chooseDeckStrategy(any(Strategy.class));
    }
}