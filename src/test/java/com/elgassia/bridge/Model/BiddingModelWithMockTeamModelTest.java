package com.elgassia.bridge.Model;

import com.elgassia.bridge.exception.BridgeLogicException;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BiddingModelWithMockTeamModelTest {

    TeamModel teamModel;
    BiddingModel biddingModel;
    @Before
    public void setUp()
    {
        teamModel=mock(TeamModel.class);
        int []x=new int[4];
        for(int i=0;i<4;i++)
            x[i]=i;
        when(teamModel.getPlayerOrder()).thenReturn(x);
        biddingModel=new BiddingModel(teamModel);
    }
    @Test
    public void testBidOrder() throws Exception {
        int x=biddingModel.getCurrentPlayerID();
        assertTrue("Bidding when its this player turn failed",biddingModel.bid(new Bid(BidType.CARD,1,Color.CLUBS),x));
        assertTrue("Bidding when its this player turn failed",biddingModel.bid(new Bid(BidType.CARD,2,Color.CLUBS),(x+1)%4));
        assertTrue("Bidding when its this player turn failed",biddingModel.bid(new Bid(BidType.CARD,3,Color.CLUBS),(x+2)%4));
        boolean y=false;
        try {
            biddingModel.bid(new Bid(BidType.CARD,4,Color.CLUBS),x);
        }catch (BridgeLogicException e)
        {
            y=true;
        }
        assertTrue("Bidding when its somebody else turn failed",y);
    }
    @Test
    public void testBidCardCounts() throws Exception {
        int x = biddingModel.getCurrentPlayerID();
        assertTrue("Bidding when card is bigger failed", biddingModel.bid(new Bid(BidType.CARD, 1, Color.CLUBS), x));
        assertTrue("Bidding when card is bigger failed", biddingModel.bid(new Bid(BidType.CARD, 1, Color.DIAMONDS), (x + 1) % 4));
        assertTrue("Bidding when card is bigger failed", biddingModel.bid(new Bid(BidType.CARD, 1, Color.NT), (x + 2) % 4));
        boolean y = false;
        try {
            biddingModel.bid(new Bid(BidType.CARD, 1, Color.CLUBS), x);
        } catch (BridgeLogicException e) {
            y = true;
        }
        assertTrue("Bidding when card is smaller failed", y);
    }

    @Test
    public void testBidContraInGoodMoment() throws Exception
    {
        int x = biddingModel.getCurrentPlayerID();
        biddingModel.bid(new Bid(BidType.CARD, 1, Color.CLUBS), x);
        assertTrue("Contra after card failed",biddingModel.bid(new Bid(BidType.CONTRA,0,null),(x+1)%4));
        biddingModel.bid(new Bid(BidType.CARD, 2, Color.CLUBS), (x+2)%4);
        biddingModel.bid(new Bid(BidType.CARD, 2, Color.HEARTS), (x+3)%4);
        biddingModel.bid(new Bid(BidType.PASS, 0, null), x);
        assertTrue("Contra after card and partner not-contra failed",biddingModel.bid(new Bid(BidType.CONTRA,0,null),(x+1)%4));
    }
    @Test
    public void testBidContraInBadMoment() throws Exception{
        int x = biddingModel.getCurrentPlayerID();
        biddingModel.bid(new Bid(BidType.PASS, 0, null), x);
        boolean y=false;
        try {
            biddingModel.bid(new Bid(BidType.CONTRA, 0, null), (x + 1) % 4);
        }
        catch (BridgeLogicException e)
        {
            y=true;
        }
        assertTrue("Contra after not-card didn't throw an exception",y);
        biddingModel.bid(new Bid(BidType.CARD, 2, Color.CLUBS), (x + 1) % 4);
        biddingModel.bid(new Bid(BidType.CONTRA, 0, null), (x+2)%4);
        biddingModel.bid(new Bid(BidType.PASS, 0, null), (x+3)%4);
        y=false;
        try {
            biddingModel.bid(new Bid(BidType.CONTRA, 0, null), x % 4);
        }
        catch (BridgeLogicException e){
            y=true;
        }
        assertTrue("Contra after partners contra didn't throw an exception",y);
    }
}