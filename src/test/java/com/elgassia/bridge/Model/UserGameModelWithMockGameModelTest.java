package com.elgassia.bridge.Model;

import com.elgassia.bridge.exception.BridgeLogicException;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

public class UserGameModelWithMockGameModelTest {

    GameModel gameModel;
    UserGameModel userGameModel;
    @Before
    public void setUp()
    {
        gameModel=mock(GameModel.class);
        userGameModel=new UserGameModel(0,gameModel);
    }
    @Test
    public void testPlayCard() throws Exception {
        when(gameModel.playCard(anyInt(),any(Card.class))).thenReturn(true).thenThrow(new BridgeLogicException());
        assertTrue("playCard method failed",userGameModel.playCard(new Card(Card.Rank.ACE, Card.Suit.CLUBS)));
        verify(gameModel).playCard(0,new Card(Card.Rank.ACE, Card.Suit.CLUBS));
        boolean x=false;
        try {
            userGameModel.playCard(new Card(Card.Rank.ACE, Card.Suit.CLUBS));
        }catch (BridgeLogicException e)
        {
            x=true;
        }
        assertTrue("playCard method failed when received exception from gameModel",x);
    }
    @Test
    public void testGetGranpasDeck() throws Exception
    {
        userGameModel.getGranpasDeck();
        verify(gameModel,times(1)).getGrandpasDeck();
    }
    @Test
    public void testGetMyDeck() throws Exception
    {
        userGameModel.getMyDeck();
        verify(gameModel,times(1)).getUserDeck(0);
    }
    @Test
    public void testGetTurnHistory() throws Exception
    {
        userGameModel.getTurnHistory();
        verify(gameModel,times(1)).getTurnHistory();
    }
    @Test
    public void testWhoStartedTurn()
    {
        userGameModel.whoStartedTurn();
        verify(gameModel,times(1)).whoStartedTurn();
    }
    @Test
    public void testGetCurrentPlayerID(){
        userGameModel.getCurrentPlayerID();
        verify(gameModel,times(1)).getCurrentPlayerID();
    }
    @Test
    public void testGetCurrentTurnNumber() {
        userGameModel.getCurrentTurnNumber();
        verify(gameModel,times(1)).getCurrentTurnNumber();
    }
    @Test
    public void testGetHowManyTurnsWereWonBy() throws Exception
    {
        when(gameModel.getHowManyTurnsWereWonBy(1)).thenReturn(2);
        when(gameModel.getHowManyTurnsWereWonBy(-1)).thenThrow(new BridgeLogicException());
        assertEquals("getHowManyTurnsWereWonBy failed with ok team number",userGameModel.getHowManyTurnsWereWonBy(1),2);
        boolean y=false;
        try {
            userGameModel.getHowManyTurnsWereWonBy(-1);
        }catch (BridgeLogicException e){
            y=true;
        }
        assertTrue("getHowManyTurnsWereWon didn't throw an exception when gameModel throws one",y);
    }

    @Test
    public void testGetPreviousTurnHistory() throws Exception
    {
        Card []x=new Card[4];
        when(gameModel.getPreviousTurnHistory()).thenThrow(new BridgeLogicException()).thenReturn(x);
        boolean y=false;
        try {
            userGameModel.getPreviousTurnHistory();
        }catch (BridgeLogicException e){
            y=true;
        }
        assertTrue("getPreviousTurnHistory didn't throw an exception when in gameModel exception was thrown",y);
        assertEquals("getPreviousTurnHistory failed",x,userGameModel.getPreviousTurnHistory());
    }
}