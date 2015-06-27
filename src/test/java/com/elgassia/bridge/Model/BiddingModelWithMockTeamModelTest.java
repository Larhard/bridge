package com.elgassia.bridge.Model;

import com.elgassia.bridge.exception.BridgeLogicException;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.*;

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
        assertTrue("Contra after card failed", biddingModel.bid(new Bid(BidType.CONTRA, 0, null), (x + 1) % 4));
        biddingModel.bid(new Bid(BidType.CARD, 2, Color.CLUBS), (x + 2) % 4);
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
    @Test
    public void testBidReContraInGoodMoment() throws Exception
    {
        int x = biddingModel.getCurrentPlayerID();
        biddingModel.bid(new Bid(BidType.CARD, 1, Color.CLUBS), x);
        biddingModel.bid(new Bid(BidType.CONTRA,0,null),(x+1)%4);
        assertTrue("Recontra after contra failed", biddingModel.bid(new Bid(BidType.RECONTRA, 0, null), (x + 2) % 4));
        biddingModel.bid(new Bid(BidType.CARD, 2, Color.CLUBS), (x+3)%4);
        biddingModel.bid(new Bid(BidType.CONTRA, 0, null), x);
        biddingModel.bid(new Bid(BidType.CARD, 3, Color.DIAMONDS), (x+1)%4);
        biddingModel.bid(new Bid(BidType.PASS, 0, null), (x+2)%4);
        assertTrue("Reontra after contra and partner not-recontra failed",biddingModel.bid(new Bid(BidType.RECONTRA,0,null),(x+3)%4));
    }
    @Test
    public void testBidRecontraInBadMoment() throws Exception{
        int x = biddingModel.getCurrentPlayerID();
        biddingModel.bid(new Bid(BidType.CARD, 4, Color.NT), x);
        boolean y=false;
        try {
            biddingModel.bid(new Bid(BidType.RECONTRA, 0, null), (x + 1) % 4);
        }
        catch (BridgeLogicException e)
        {
            y=true;
        }
        assertTrue("Recontra after not-contra didn't throw an exception",y);
        biddingModel.bid(new Bid(BidType.CARD, 5, Color.CLUBS), (x + 1) % 4);
        biddingModel.bid(new Bid(BidType.CONTRA, 0, null), (x+2)%4);
        biddingModel.bid(new Bid(BidType.RECONTRA, 0, null), (x+3)%4);
        biddingModel.bid(new Bid(BidType.PASS, 0, null), x);
        y=false;
        try {
            biddingModel.bid(new Bid(BidType.RECONTRA, 0, null), (x+1)%4);
        }
        catch (BridgeLogicException e){
            y=true;
        }
        assertTrue("Recontra after partners recontra didn't throw an exception",y);
    }
    @Test
    public void testBidPassInAnyMoment() throws Exception
    {
        int x = biddingModel.getCurrentPlayerID();
        biddingModel.bid(new Bid(BidType.CARD, 1, Color.CLUBS), x);
        assertTrue("Pass after card failed", biddingModel.bid(new Bid(BidType.PASS, 0, null), (x + 1) % 4));
        biddingModel.bid(new Bid(BidType.CARD, 2, Color.CLUBS), (x+2)%4);
        biddingModel.bid(new Bid(BidType.CONTRA, 0, null), (x + 3) % 4);
        assertTrue("Pass after contra failed",biddingModel.bid(new Bid(BidType.PASS,0,null),x));
        biddingModel.bid(new Bid(BidType.CARD, 4, Color.CLUBS), (x+1)%4);
        biddingModel.bid(new Bid(BidType.RECONTRA, 0, null), (x+2)%4);
        assertTrue("Pass after recontra failed",biddingModel.bid(new Bid(BidType.PASS,0,null),(x+3)%4));
        assertTrue("Pass after pass failed",biddingModel.bid(new Bid(BidType.PASS,0,null),x));
    }
    @Test
    public void testBidFourPassesInARowAtTheBeggining() throws Exception
    {
        int x = biddingModel.getCurrentPlayerID();
        biddingModel.bid(new Bid(BidType.PASS,0,null),x);
        biddingModel.bid(new Bid(BidType.PASS,0,null),(x+1)%4);
        biddingModel.bid(new Bid(BidType.PASS,0,null),(x+2)%4);
        biddingModel.bid(new Bid(BidType.PASS,0,null),(x+3)%4);
        verify(teamModel,times(1)).changeGameState('B');
    }
    @Test
    public void testBidThreePassesInARowAfterACard() throws Exception
    {
        int x = biddingModel.getCurrentPlayerID();
        biddingModel.bid(new Bid(BidType.CARD,7,Color.NT),x);
        biddingModel.bid(new Bid(BidType.PASS,0,null),(x+1)%4);
        biddingModel.bid(new Bid(BidType.PASS,0,null),(x+2)%4);
        biddingModel.bid(new Bid(BidType.PASS,0,null),(x+3)%4);
        verify(teamModel,times(1)).changeGameState('G');
        verify(teamModel,times(1)).setBiddingWinner(x);
        verify(teamModel,times(1)).setAtu(Color.NT);
        verify(teamModel,times(1)).setContract(7);
    }
    @Test
    public void testGetBiddingHistory() throws Exception
    {
        assertEquals("getBiddingHistory with empty list failed",new LinkedList<Bid>(),biddingModel.getBiddingHistory());
        int x=biddingModel.getCurrentPlayerID();
        biddingModel.bid(new Bid(BidType.CARD,3,Color.SPADES),x);
        biddingModel.bid(new Bid(BidType.CARD, 5, Color.CLUBS), (x + 1) % 4);
        biddingModel.bid(new Bid(BidType.CONTRA, 0, null), (x+2)%4);
        biddingModel.bid(new Bid(BidType.RECONTRA, 0, null), (x+3)%4);
        biddingModel.bid(new Bid(BidType.PASS, 0, null), x);
        List<Bid> bids=new LinkedList<>();
        bids.add(new Bid(BidType.CARD,3,Color.SPADES));
        bids.add(new Bid(BidType.CARD, 5, Color.CLUBS));
        bids.add(new Bid(BidType.CONTRA, 0, null));
        bids.add(new Bid(BidType.RECONTRA, 0, null));
        bids.add(new Bid(BidType.PASS, 0, null));
        assertEquals("getBiddingHistory failed",bids,biddingModel.getBiddingHistory());
    }
}