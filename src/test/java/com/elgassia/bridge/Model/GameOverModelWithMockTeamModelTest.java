package com.elgassia.bridge.Model;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class GameOverModelWithMockTeamModelTest {

    TeamModel teamModel;
    GameOverModel gameOverModel;
    @Before
    public void setUp()
    {
        teamModel=mock(TeamModel.class);
        gameOverModel=new GameOverModel(teamModel);
    }
    @Test
    public void testGetGameWinner() throws Exception {
        gameOverModel.getGameWinner();
        verify(teamModel,times(1)).getGameWinner();
    }

    @Test
    public void testGetTurnsWonByThePlayingTeam() throws Exception {
        gameOverModel.getTurnsWonByThePlayingTeam();
        verify(teamModel,times(1)).getTurnsWonByThePlayingTeam();
    }

    @Test
    public void testGetPlayedCards() throws Exception {
        gameOverModel.getPlayedCards();
        verify(teamModel,times(1)).getPlayedCards();
    }

    @Test
    public void testGetWhoStartedTurn() throws Exception {
        gameOverModel.getWhoStartedTurn();
        verify(teamModel,times(1)).getWhoStartedTurn();
    }
}