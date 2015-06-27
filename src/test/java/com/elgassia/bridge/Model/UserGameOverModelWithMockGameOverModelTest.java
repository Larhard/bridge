package com.elgassia.bridge.Model;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class UserGameOverModelWithMockGameOverModelTest {

    GameOverModel gameOverModel;
    UserGameOverModel userGameOverModel;
    @Before
    public void setUp()
    {
        gameOverModel=mock(GameOverModel.class);
        userGameOverModel=new UserGameOverModel(0,gameOverModel);
    }
    @Test
    public void testGetGameWinner() throws Exception {
        userGameOverModel.getGameWinner();
        verify(gameOverModel,times(1)).getGameWinner();
    }

    @Test
    public void testGetTurnsWonByThePlayingTeam() throws Exception {
        userGameOverModel.getTurnsWonByThePlayingTeam();
        verify(gameOverModel,times(1)).getTurnsWonByThePlayingTeam();
    }

    @Test
    public void testGetPlayedCards() throws Exception {
        userGameOverModel.getPlayedCards();
        verify(gameOverModel,times(1)).getPlayedCards();
    }

    @Test
    public void testGetWhoStartedTurn() throws Exception {
        userGameOverModel.getWhoStartedTurn();
        verify(gameOverModel,times(1)).getWhoStartedTurn();
    }
}