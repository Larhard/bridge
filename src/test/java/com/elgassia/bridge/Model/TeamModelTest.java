package com.elgassia.bridge.Model;

import com.elgassia.bridge.exception.BridgeLogicException;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class TeamModelTest {

    TeamModel teamModel;
    @Before
    public void setUp()
    {
        teamModel=new TeamModel();
    }
    @Test
    public void testChangeGameState() throws Exception {
        teamModel.changeGameState('B');
        assertEquals("changeGameState failed", teamModel.getState(), 'B');
        teamModel.changeGameState('G');
        assertEquals("changeGameState failed", teamModel.getState(), 'G');
        teamModel.changeGameState('O');
        assertEquals("changeGameState failed", teamModel.getState(), 'O');
    }

    @Test
    public void testGetState() throws Exception {
        assertEquals("getState failed", teamModel.getState(), 'L');
    }

    @Test
    public void testSetName() throws Exception {
        teamModel.setName(0,"Ala");
        teamModel.setName(1,"Ma");
        teamModel.setName(2,"Kota");
        teamModel.setName(3,"BoTak");
        assertEquals("setName failed","Ala",teamModel.getPlayerName(0));
        assertEquals("setName failed","Ma",teamModel.getPlayerName(1));
        assertEquals("setName failed","Kota",teamModel.getPlayerName(2));
        assertEquals("setName failed","BoTak",teamModel.getPlayerName(3));
    }

    @Test
    public void testSetTeamWithWrongValue() throws Exception {
        boolean y=false;
        try {
            teamModel.setTeam(0,-10);
        }catch (BridgeLogicException e)
        {
            y=true;
        }
        assertTrue("setTeam didn't throw an exception when called with wrong argument",y);
        y=false;
        try {
            teamModel.setTeam(0,3);
        }catch (BridgeLogicException e)
        {
            y=true;
        }
        assertTrue("setTeam didn't throw an exception when called with wrong argument",y);
    }

    @Test
    public void testSetTeamWithoutChanges() throws Exception {
        assertTrue("setTeam failed",teamModel.setTeam(0,0));
        assertTrue("setTeam failed",teamModel.setTeam(1,0));
        assertTrue("setTeam failed",teamModel.setTeam(2,1));
        assertTrue("setTeam failed",teamModel.setTeam(3,1));
    }

    @Test
    public void testSetTeamWithChanges() throws Exception {
        assertTrue("setTeam failed",teamModel.setTeam(0,0));
        assertTrue("setTeam failed",teamModel.setTeam(1,0));
        assertTrue("setTeam failed",teamModel.setTeam(0,1));
        assertTrue("setTeam failed",teamModel.setTeam(3,1));
        boolean y=false;
        try {
            teamModel.setTeam(2,1);
        }catch (BridgeLogicException e)
        {
            y=true;
        }
        assertTrue("setTeam didn't throw an exception when team is full",y);
    }

    @Test
    public void testGetUserTeam() throws Exception {
        teamModel.setTeam(0, 0);
        teamModel.setTeam(1, 0);
        teamModel.setTeam(2, 1);
        teamModel.setTeam(3, 1);
        assertEquals("getTeam failed",teamModel.getUserTeam(0),0);
        assertEquals("getTeam failed",teamModel.getUserTeam(1),0);
        assertEquals("getTeam failed",teamModel.getUserTeam(2),1);
        assertEquals("getTeam failed",teamModel.getUserTeam(3),1);
    }

    @Test
    public void testGetPlayerName() throws Exception {
        assertEquals("getPlayerName failed",teamModel.getPlayerName(0),"user1");
        assertEquals("getPlayerName failed",teamModel.getPlayerName(1),"user2");
        assertEquals("getPlayerName failed",teamModel.getPlayerName(2),"user3");
        assertEquals("getPlayerName failed",teamModel.getPlayerName(3),"user4");
    }

    @Test
    public void testGetPlayerCards() throws Exception {
        teamModel.chooseDeckStrategy(new RandomStrategy());
        teamModel.drawCards();
        assertEquals("getPlayerCards failed",teamModel.getPlayerCards(0).size(),13);
        assertEquals("getPlayerCards failed",teamModel.getPlayerCards(1).size(),13);
        assertEquals("getPlayerCards failed",teamModel.getPlayerCards(2).size(),13);
        assertEquals("getPlayerCards failed",teamModel.getPlayerCards(3).size(),13);
    }

    @Test
    public void testDrawCards() throws Exception {
        Strategy strategy=mock(Strategy.class);
        teamModel.chooseDeckStrategy(strategy);
        teamModel.drawCards();
        verify(strategy,times(1)).getCards();
    }

    @Test
    public void testGetPlayerOrder() throws Exception {
        int [] order=teamModel.getPlayerOrder();
        assertEquals("getPlayerOrder failed",order.length,4);
        boolean y=false;
        for(int i=0;i<4;i++)
        {
            if(order[i]==0)
            {
                y=true;
                break;
            }
        }
        assertTrue("getPlayerOrder failed",y);
        y=false;
        for(int i=0;i<4;i++)
        {
            if(order[i]==1)
            {
                y=true;
                break;
            }
        }
        assertTrue("getPlayerOrder failed",y);
        y=false;
        for(int i=0;i<4;i++)
        {
            if(order[i]==2)
            {
                y=true;
                break;
            }
        }
        assertTrue("getPlayerOrder failed",y);
        y=false;
        for(int i=0;i<4;i++)
        {
            if(order[i]==3)
            {
                y=true;
                break;
            }
        }
        assertTrue("getPlayerOrder failed",y);
    }

    @Test
    public void testSetBiddingWinner() throws Exception {
        teamModel.setBiddingWinner(0);
        assertEquals("setBiddingWinner failed",teamModel.getBiddingWinner(),0);
    }

    @Test
    public void testSetAtu() throws Exception {
        teamModel.setAtu(Color.NT);
        assertEquals("setAtu failed",teamModel.getAtu(),Color.NT);
    }

    @Test
    public void testSetContract() throws Exception {
        teamModel.setContract(5);
        assertEquals("setContract failed",teamModel.getContract(),5);
    }

    @Test
    public void testDeleteCard() throws Exception {
        teamModel.chooseDeckStrategy(new RandomStrategy());
        teamModel.drawCards();
        List<Card> list=teamModel.getPlayerCards(0);
        Card x=list.get(0);
        teamModel.deleteCard(0,x);
        assertFalse("deleteCard failed",teamModel.getPlayerCards(0).contains(x));
        assertEquals("deleteCard failed",teamModel.getPlayerCards(0).size(),12);
    }

    @Test
    public void testCheckForCard() throws Exception {
        teamModel.chooseDeckStrategy(new RandomStrategy());
        teamModel.drawCards();
        List<Card> list1=teamModel.getPlayerCards(0);
        List<Card> list2=teamModel.getPlayerCards(1);
        assertTrue("checkForCard failed",teamModel.checkForCard(0,list1.get(7)));
        assertFalse("checkForCard failed", teamModel.checkForCard(0, list2.get(7)));
    }

    @Test
    public void testCheckForCardColor() throws Exception {
        teamModel.chooseDeckStrategy(new RandomStrategy());
        teamModel.drawCards();
        List<Card> list1=teamModel.getPlayerCards(0);
        assertTrue("checkForCardColor failed",teamModel.checkForCardColor(0,list1.get(7).getSuit()));
    }

    @Test
    public void testSetGameWinner() throws Exception {
        teamModel.setTeam(0,0);
        teamModel.setGameWinner(0);
        assertEquals("setGameWinnerFailed",teamModel.getGameWinner(),0);
    }

    @Test
    public void testSetHowManyTurnsPlayingTeamWon() throws Exception {
        teamModel.setHowManyTurnsPlayingTeamWon(4);
        assertEquals("setHowManyTurnsPlayingTeamWon",teamModel.getTurnsWonByThePlayingTeam(),4);
    }

    @Test
    public void testSetGameHistory() throws Exception {
        Card [][] cards=new Card[13][4];
        int win[]=new int[13];
        teamModel.setGameHistory(cards,win);
        assertArrayEquals("setGameHistoryFailed", teamModel.getPlayedCards(), cards);
        assertArrayEquals("setGameHistoryFailed",teamModel.getWhoStartedTurn(),win);
    }
}