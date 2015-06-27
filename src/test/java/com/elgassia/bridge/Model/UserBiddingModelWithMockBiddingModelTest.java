package com.elgassia.bridge.Model;

import com.elgassia.bridge.exception.BridgeLogicException;
import org.junit.Test;

import static junit.framework.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

public class UserBiddingModelWithMockBiddingModelTest {

    BiddingModel biddingModel=mock(BiddingModel.class);
    UserBiddingModel userBiddingModel=new UserBiddingModel(0,biddingModel);

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
}