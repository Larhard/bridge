package com.elgassia.bridge.Model;

import com.elgassia.bridge.exception.BridgeLogicException;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;

import static junit.framework.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

public class UserBiddingModelWithMockBiddingModelTest {

    BiddingModel biddingModel;
    UserBiddingModel userBiddingModel;
    @Before
    public void setUp()
    {
        biddingModel=mock(BiddingModel.class);
        when(biddingModel.getPlayerCards(0)).thenReturn(new LinkedList<Card>());
        userBiddingModel=new UserBiddingModel(0,biddingModel);
    }
    @Test
    public void testBid() throws Exception {
        when(biddingModel.bid(any(Bid.class),anyInt())).thenReturn(true).thenThrow(new BridgeLogicException());
        assertTrue("bid method failed",userBiddingModel.bid(new Bid(BidType.CARD,3,Color.CLUBS)));
        verify(biddingModel, times(1)).bid(new Bid(BidType.CARD, 3, Color.CLUBS), 0);
        boolean x=false;
        try {
            userBiddingModel.bid(new Bid(BidType.CARD,3,Color.CLUBS));
        }
        catch (BridgeLogicException e)
        {
            x=true;
        }
        assertTrue("bid method with exception from BiddingModel failed",x);
    }
    @Test
    public void testGetMyDeck() throws Exception
    {
        userBiddingModel.getMyDeck();
        verify(biddingModel,times(1)).getPlayerCards(0);
    }
    @Test
    public void testGetBiddingHistory() throws Exception
    {
        userBiddingModel.getBiddingHistory();
        verify(biddingModel,times(1)).getBiddingHistory();
    }
    @Test
    public void testCurrentPlayer() throws Exception
    {
        userBiddingModel.getCurrentPlayer();
        verify(biddingModel,times(1)).getCurrentPlayer();
    }
    @Test
    public void testCurrentPlayerID() throws Exception
    {
        userBiddingModel.getCurrentPlayerID();
        verify(biddingModel,times(1)).getCurrentPlayerID();
    }
}