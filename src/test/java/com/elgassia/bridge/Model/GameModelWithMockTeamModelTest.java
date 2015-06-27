package com.elgassia.bridge.Model;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class GameModelWithMockTeamModelTest {

    TeamModel teamModel;
    GameModel gameModel;
    @Before
    public void setUp()
    {
        teamModel=mock(TeamModel.class);
        gameModel=new GameModel(teamModel);
    }
    @Test
    public void testPlayCard() throws Exception {

    }

    @Test
    public void testGetGrandpasDeck() throws Exception {

    }

    @Test
    public void testGetUserDeck() throws Exception {

    }

    @Test
    public void testGetTurnHistory() throws Exception {

    }

    @Test
    public void testWhoStartedTurn() throws Exception {

    }
}