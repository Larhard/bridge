package com.elgassia.bridge.Model;

import com.elgassia.bridge.exception.BridgeLogicException;
import junit.framework.TestCase;

public class LobbyModelTest extends TestCase {
    TeamModel teamModel;
    LobbyModel lobbyModel;
    UserLobbyModel userLobbyModel1;
    UserLobbyModel userLobbyModel2;
    UserLobbyModel userLobbyModel3;
    UserLobbyModel userLobbyModel4;
    @Override
    protected void setUp() throws Exception {
        teamModel=new TeamModel();
        lobbyModel=new LobbyModel(teamModel);
        userLobbyModel1=new UserLobbyModel(0,lobbyModel);
        userLobbyModel2=new UserLobbyModel(1,lobbyModel);
        userLobbyModel3=new UserLobbyModel(2,lobbyModel);
        userLobbyModel4=new UserLobbyModel(3,lobbyModel);
    }
    public void testSetName() throws BridgeLogicException {
        assertTrue(userLobbyModel1.setName("Ala"));
        assertTrue(userLobbyModel2.setName("Ma"));
        assertTrue(userLobbyModel3.setName("Kota"));
        assertTrue(userLobbyModel4.setName("BoTak"));
        assertEquals(teamModel.getPlayerName(0), "Ala");
        assertEquals(teamModel.getPlayerName(1),"Ma");
        assertEquals(teamModel.getPlayerName(2),"Kota");
        assertEquals(teamModel.getPlayerName(3),"BoTak");
    }
    public void setNameWhenReady() throws BridgeLogicException {
        assertFalse(userLobbyModel1.ready());
        try {
            assertTrue(userLobbyModel1.setTeam(0));
            assertEquals(teamModel.getUserTeam(0),0);
        }
        catch (BridgeLogicException e){
            assertTrue("User setTeam failed",false);
        }
        try {
            assertTrue(userLobbyModel2.setTeam(0));
            assertEquals(teamModel.getUserTeam(1),0);
        }
        catch (BridgeLogicException e){
            assertTrue("User setTeam failed",false);
        }
        try {
            assertTrue(userLobbyModel3.setTeam(1));
            assertEquals(teamModel.getUserTeam(2),1);
        }
        catch (BridgeLogicException e){
            assertTrue("User setTeam failed",false);
        }
        try {
            assertTrue(userLobbyModel4.setTeam(1));
            assertEquals(teamModel.getUserTeam(3),1);
        }
        catch (BridgeLogicException e){
            assertTrue("User setTeam failed", false);
        }
        assertTrue(userLobbyModel1.ready());
        assertFalse(userLobbyModel1.setName("Alicja"));
    }
}