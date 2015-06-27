package com.elgassia.bridge.Model;

import com.elgassia.bridge.exception.BridgeLogicException;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
}