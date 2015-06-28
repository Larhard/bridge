package com.elgassia.bridge.Model;

import com.elgassia.bridge.exception.BridgeLogicException;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class GameModelWithMockTeamModelTest {

    TeamModel teamModel;
    GameModel gameModel;
    @Before
    public void setUp()
    {
        teamModel=mock(TeamModel.class);
        int [] x=new int[4];
        for(int i=0;i<4;i++)
            x[i]=i;
        when(teamModel.getPlayerOrder()).thenReturn(x);
        when(teamModel.getBiddingWinner()).thenReturn(0);
        when(teamModel.getAtu()).thenReturn(Color.DIAMONDS);
        when(teamModel.getContract()).thenReturn(3);
        gameModel=new GameModel(teamModel);
    }
    @Test
    public void testPlayCardWhenNoOneElsePlayed() throws Exception {
        when(teamModel.checkForCard(1,new Card(Card.Rank.FIVE, Card.Suit.HEARTS))).thenReturn(true);
        assertTrue("Playing card as first player failed", gameModel.playCard(1, new Card(Card.Rank.FIVE, Card.Suit.HEARTS)));
        verify(teamModel,times(1)).deleteCard(1,new Card(Card.Rank.FIVE, Card.Suit.HEARTS));
    }
    @Test
    public void testPlayCardYouDontHave() throws Exception {
        when(teamModel.checkForCard(1,new Card(Card.Rank.FIVE, Card.Suit.HEARTS))).thenReturn(false);
        boolean y=false;
        try {
            gameModel.playCard(1, new Card(Card.Rank.FIVE, Card.Suit.HEARTS));
        }catch (BridgeLogicException e){
            y=true;
        }
        assertTrue("Playing card you don't have didn't throw an exception",y);
    }
    @Test
    public void testPlayCardWhenItsNotYourTurn()
    {
        boolean y=false;
        try {
            gameModel.playCard(3, new Card(Card.Rank.FIVE, Card.Suit.HEARTS));
        }catch (BridgeLogicException e){
            y=true;
        }
        assertTrue("Playing card when it's not your turn have didn't throw an exception",y);
    }
    @Test
    public void testPlayCardWhenItsGrandpasTurn() throws Exception
    {
        when(teamModel.checkForCard(1,new Card(Card.Rank.FOUR, Card.Suit.HEARTS))).thenReturn(true);
        gameModel.playCard(1, new Card(Card.Rank.FOUR, Card.Suit.HEARTS));
        boolean y=false;
        try {
            gameModel.playCard(2, new Card(Card.Rank.FIVE, Card.Suit.HEARTS));
        }catch (BridgeLogicException e){
            y=true;
        }
        assertTrue("Playing card when it's granpas turn and you're grandpa didn't throw an exception",y);
        y=false;
        try {
            gameModel.playCard(3, new Card(Card.Rank.FIVE, Card.Suit.HEARTS));
        }catch (BridgeLogicException e){
            y=true;
        }
        assertTrue("Playing card when it's granpas turn and you're not his partner didn't throw an exception",y);
        when(teamModel.checkForCard(2,new Card(Card.Rank.FIVE, Card.Suit.HEARTS))).thenReturn(true);
        assertTrue("Playing grandpas cards when you're his partner failed", gameModel.playCard(0, new Card(Card.Rank.FIVE, Card.Suit.HEARTS)));
    }
    @Test
    public void testPlayCardWithWrongColorWhenYouDontHaveGoodColorOnHand() throws Exception {
        when(teamModel.checkForCard(anyInt(), any(Card.class))).thenReturn(true).thenReturn(true);
        when(teamModel.checkForCardColor(anyInt(),any(Card.Suit.class))).thenReturn(false);
        gameModel.playCard(1, new Card(Card.Rank.FIVE, Card.Suit.HEARTS));
        assertTrue("Playing card with different color when you don't have good color on hand failed", gameModel.playCard(0, new Card(Card.Rank.TWO, Card.Suit.SPADES)));
    }
    @Test
    public void testPlayCardWithWrongColorWhenYouHaveGoodColorOnHand() throws Exception {
        when(teamModel.checkForCard(anyInt(), any(Card.class))).thenReturn(true).thenReturn(true);
        when(teamModel.checkForCardColor(anyInt(),any(Card.Suit.class))).thenReturn(true);
        gameModel.playCard(1, new Card(Card.Rank.FIVE, Card.Suit.HEARTS));
        boolean y=false;
        try {
            gameModel.playCard(0, new Card(Card.Rank.TWO, Card.Suit.SPADES));
        }
        catch (BridgeLogicException e)
        {
            y=true;
        }
        assertTrue("Playing card with different color when you have the good one didn't throw an exception",y);
    }
    @Test
    public void testPlayCardWholeTurnWithTheSameColorAndCheckForTheWinner() throws Exception
    {
        when(teamModel.checkForCard(anyInt(),any(Card.class))).thenReturn(true).thenReturn(true).thenReturn(true).thenReturn(true).thenReturn(true);
        gameModel.playCard(1, new Card(Card.Rank.FIVE, Card.Suit.HEARTS));
        assertTrue("Playing card with the same color failed",gameModel.playCard(0,new Card(Card.Rank.TWO, Card.Suit.HEARTS)));
        assertTrue("Playing card with the same color failed",gameModel.playCard(3,new Card(Card.Rank.ACE, Card.Suit.HEARTS)));
        assertTrue("Playing card with the same color failed",gameModel.playCard(0,new Card(Card.Rank.NINE, Card.Suit.HEARTS)));
        assertTrue("Playing card in next turn when you're the winner failed",gameModel.playCard(3,new Card(Card.Rank.SEVEN, Card.Suit.CLUBS)));
    }
    @Test
    public void testGetGrandpasDeck() throws Exception {
        gameModel.getGrandpasDeck();
        verify(teamModel,times(1)).getPlayerCards(2);
    }

    @Test
    public void testGetUserDeck() throws Exception {
        gameModel.getUserDeck(3);
        verify(teamModel,times(1)).getPlayerCards(3);
    }

    @Test
    public void testGetTurnHistory() throws Exception {
        Card [] hist;
        when(teamModel.checkForCard(anyInt(),any(Card.class))).thenReturn(true).thenReturn(true).thenReturn(true).thenReturn(true).thenReturn(true);
        hist=gameModel.getTurnHistory();
        assertEquals("getTurnHistoryFailed",hist[0],null);
        assertEquals("getTurnHistoryFailed",hist[1],null);
        assertEquals("getTurnHistoryFailed",hist[2],null);
        assertEquals("getTurnHistoryFailed",hist[3],null);
        gameModel.playCard(1, new Card(Card.Rank.FIVE, Card.Suit.HEARTS));
        hist=gameModel.getTurnHistory();
        assertEquals("getTurnHistoryFailed",hist[0],new Card(Card.Rank.FIVE, Card.Suit.HEARTS));
        assertEquals("getTurnHistoryFailed",hist[1],null);
        assertEquals("getTurnHistoryFailed",hist[2],null);
        assertEquals("getTurnHistoryFailed",hist[3],null);
        gameModel.playCard(0, new Card(Card.Rank.TWO, Card.Suit.HEARTS));
        hist=gameModel.getTurnHistory();
        assertEquals("getTurnHistoryFailed",hist[0],new Card(Card.Rank.FIVE, Card.Suit.HEARTS));
        assertEquals("getTurnHistoryFailed",hist[1],new Card(Card.Rank.TWO, Card.Suit.HEARTS));
        assertEquals("getTurnHistoryFailed",hist[2],null);
        assertEquals("getTurnHistoryFailed",hist[3],null);
        gameModel.playCard(3, new Card(Card.Rank.ACE, Card.Suit.HEARTS));
        hist=gameModel.getTurnHistory();
        assertEquals("getTurnHistoryFailed",hist[0],new Card(Card.Rank.FIVE, Card.Suit.HEARTS));
        assertEquals("getTurnHistoryFailed",hist[1],new Card(Card.Rank.TWO, Card.Suit.HEARTS));
        assertEquals("getTurnHistoryFailed",hist[2],new Card(Card.Rank.ACE, Card.Suit.HEARTS));
        assertEquals("getTurnHistoryFailed",hist[3],null);
        gameModel.playCard(0, new Card(Card.Rank.NINE, Card.Suit.HEARTS));
        hist=gameModel.getTurnHistory();
        assertEquals("getTurnHistoryFailed",hist[0],null);
        assertEquals("getTurnHistoryFailed",hist[1],null);
        assertEquals("getTurnHistoryFailed",hist[2],null);
        assertEquals("getTurnHistoryFailed",hist[3],null);
        gameModel.playCard(3, new Card(Card.Rank.SEVEN, Card.Suit.CLUBS));
        hist=gameModel.getTurnHistory();
        assertEquals("getTurnHistoryFailed",hist[0],new Card(Card.Rank.SEVEN, Card.Suit.CLUBS));
        assertEquals("getTurnHistoryFailed",hist[1],null);
        assertEquals("getTurnHistoryFailed",hist[2],null);
        assertEquals("getTurnHistoryFailed",hist[3],null);
    }

    @Test
    public void testWhoStartedTurnWhenPreviousTurnWasAllTheSameColor() throws Exception {
        when(teamModel.getPlayerName(1)).thenReturn("user2");
        when(teamModel.getPlayerName(3)).thenReturn("user4");
        when(teamModel.checkForCard(anyInt(), any(Card.class))).thenReturn(true).thenReturn(true).thenReturn(true).thenReturn(true).thenReturn(true);
        assertEquals("Who started turn for first turn failed", gameModel.whoStartedTurn(), "user2");
        gameModel.playCard(1, new Card(Card.Rank.FIVE, Card.Suit.HEARTS));
        gameModel.playCard(0, new Card(Card.Rank.TWO, Card.Suit.HEARTS));
        gameModel.playCard(3, new Card(Card.Rank.ACE, Card.Suit.HEARTS));
        gameModel.playCard(0, new Card(Card.Rank.NINE, Card.Suit.HEARTS));
        gameModel.playCard(3, new Card(Card.Rank.SEVEN, Card.Suit.CLUBS));
        assertEquals("Who started turn for cards the same color failed", gameModel.whoStartedTurn(), "user4");
    }

    @Test
    public void testWhoStartedTurnWhenPreviousTurnWasDifferentColorsButNotAtu() throws Exception {
        when(teamModel.getPlayerName(0)).thenReturn("user1");
        when(teamModel.checkForCard(anyInt(), any(Card.class))).thenReturn(true).thenReturn(true).thenReturn(true).thenReturn(true).thenReturn(true);
        when(teamModel.checkForCardColor(anyInt(),any(Card.Suit.class))).thenReturn(false).thenReturn(false);
        gameModel.playCard(1, new Card(Card.Rank.FIVE, Card.Suit.HEARTS));
        gameModel.playCard(0, new Card(Card.Rank.TWO, Card.Suit.SPADES));
        gameModel.playCard(3, new Card(Card.Rank.ACE, Card.Suit.CLUBS));
        gameModel.playCard(0, new Card(Card.Rank.NINE, Card.Suit.HEARTS));
        gameModel.playCard(0, new Card(Card.Rank.SEVEN, Card.Suit.CLUBS));
        assertEquals("Who started turn for cards the same color failed", gameModel.whoStartedTurn(), "user1");
    }

    @Test
    public void testWhoStartedTurnWhenPreviousTurnWasDifferentColorsWithAtu() throws Exception {
        when(teamModel.getPlayerName(2)).thenReturn("user3");
        when(teamModel.checkForCard(anyInt(), any(Card.class))).thenReturn(true).thenReturn(true).thenReturn(true).thenReturn(true).thenReturn(true);
        when(teamModel.checkForCardColor(anyInt(),any(Card.Suit.class))).thenReturn(false).thenReturn(false);
        gameModel.playCard(1, new Card(Card.Rank.FIVE, Card.Suit.HEARTS));
        gameModel.playCard(0, new Card(Card.Rank.TWO, Card.Suit.DIAMONDS));
        gameModel.playCard(3, new Card(Card.Rank.ACE, Card.Suit.CLUBS));
        gameModel.playCard(0, new Card(Card.Rank.NINE, Card.Suit.HEARTS));
        gameModel.playCard(0, new Card(Card.Rank.SEVEN, Card.Suit.CLUBS));
        assertEquals("Who started turn for cards the same color failed", gameModel.whoStartedTurn(), "user3");
    }

    @Test
    public void testGetPreviousTurnHistory() throws Exception
    {
        when(teamModel.checkForCard(anyInt(),any(Card.class))).thenReturn(true).thenReturn(true).thenReturn(true).thenReturn(true).thenReturn(true);
        gameModel.playCard(1, new Card(Card.Rank.FIVE, Card.Suit.HEARTS));
        gameModel.playCard(0, new Card(Card.Rank.TWO, Card.Suit.HEARTS));
        boolean y=false;
        try {
            gameModel.getPreviousTurnHistory();
        }catch (BridgeLogicException e)
        {
            y=true;
        }
        assertTrue("getPreviousTurnHistory didn't fail when there were no previous turn",y);
        gameModel.playCard(3, new Card(Card.Rank.ACE, Card.Suit.HEARTS));
        gameModel.playCard(0,new Card(Card.Rank.NINE, Card.Suit.HEARTS));
        gameModel.playCard(3,new Card(Card.Rank.SEVEN, Card.Suit.CLUBS));
        Card[] hist=gameModel.getPreviousTurnHistory();
        assertEquals("getPreviousTurnHistory failed",new Card(Card.Rank.FIVE, Card.Suit.HEARTS),hist[0]);
        assertEquals("getPreviousTurnHistory failed",new Card(Card.Rank.TWO, Card.Suit.HEARTS),hist[1]);
        assertEquals("getPreviousTurnHistory failed",new Card(Card.Rank.ACE, Card.Suit.HEARTS),hist[2]);
        assertEquals("getPreviousTurnHistory failed",new Card(Card.Rank.NINE, Card.Suit.HEARTS),hist[3]);
    }
}