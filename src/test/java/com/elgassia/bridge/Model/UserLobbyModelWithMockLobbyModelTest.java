package com.elgassia.bridge.Model;

import com.elgassia.bridge.exception.BridgeLogicException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class UserLobbyModelWithMockLobbyModelTest {

    LobbyModel lobbyModel;
    UserLobbyModel userLobbyModel1;
    UserLobbyModel userLobbyModel2;
    UserLobbyModel userLobbyModel3;
    UserLobbyModel userLobbyModel4;
    @Before
    public void setUp()
    {
        lobbyModel= mock(LobbyModel.class);
        userLobbyModel1=new UserLobbyModel(0,lobbyModel);
        userLobbyModel2=new UserLobbyModel(1,lobbyModel);
        userLobbyModel3=new UserLobbyModel(2,lobbyModel);
        userLobbyModel4=new UserLobbyModel(3,lobbyModel);
    }
    @Test
    public void testSetName() throws Exception {
        when(lobbyModel.setName(anyInt(),anyString())).thenReturn(true).thenReturn(true).thenReturn(true).thenReturn(true);
        assertTrue("setName method failed", userLobbyModel1.setName("Ala"));
        verify(lobbyModel, times(1)).setName(0, "Ala");
        assertTrue("setName method failed", userLobbyModel2.setName("Ma"));
        verify(lobbyModel, times(1)).setName(1, "Ma");
        assertTrue("setName method failed",userLobbyModel3.setName("Kota"));
        verify(lobbyModel, times(1)).setName(2, "Kota");
        assertTrue("setName method failed",userLobbyModel4.setName("BoTak"));
        verify(lobbyModel, times(1)).setName(3, "BoTak");
        verify(lobbyModel, never()).setName(0, "Ma");
        when(lobbyModel.setName(anyInt(),anyString())).thenThrow(new BridgeLogicException());
        boolean x=false;
        try{
            userLobbyModel1.setName("xyz");
        }
        catch (BridgeLogicException e)
        {
            x=true;
        }
        assertTrue("setName when LobbyModel throws exception failed",x);
    }

    @Test
    public void testSetTeam() throws Exception {
        when(lobbyModel.setTeam(anyInt(), anyInt())).thenReturn(true);
        assertTrue("setTeam method failed", userLobbyModel1.setTeam(0));
        verify(lobbyModel, times(1)).setTeam(0, 0);
        when(lobbyModel.setTeam(anyInt(), anyInt())).thenThrow(new BridgeLogicException());
        boolean x=false;
        try{
            userLobbyModel1.setTeam(2);
        }
        catch (BridgeLogicException e)
        {
            x=true;
        }
        assertTrue("setTeam when LobbyModel throws exception failed",x);
    }

    @Test
    public void testRandomTeam() throws Exception {
        when(lobbyModel.randomTeam(anyInt())).thenReturn(true).thenThrow(new BridgeLogicException());
        assertTrue("randomTeam method failed", userLobbyModel1.randomTeam());
        verify(lobbyModel, times(1)).randomTeam(0);
        boolean x=false;
        try{
            userLobbyModel1.randomTeam();
        }
        catch (BridgeLogicException e)
        {
            x=true;
        }
        assertTrue("randomTeam when LobbyModel throws exception failed",x);
    }

    @Test
    public void testReady() throws Exception {
        when(lobbyModel.ready(anyInt())).thenReturn(true).thenThrow(new BridgeLogicException());
        assertTrue("ready method failed", userLobbyModel1.ready());
        verify(lobbyModel, times(1)).ready(0);
        boolean x=false;
        try{
            userLobbyModel1.ready();
        }
        catch (BridgeLogicException e)
        {
            x=true;
        }
        assertTrue("ready when LobbyModel throws exception failed",x);
    }
    @Test
    public void testCheckDeckStrategy() throws Exception
    {
        userLobbyModel1.chooseDeckStrategy(new RandomStrategy());
        verify(lobbyModel, times(1)).chooseDeckStrategy(any(RandomStrategy.class));
    }
}