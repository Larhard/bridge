package com.elgassia.bridge.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by vereena on 6/19/15.
 */
public class BiddingModel {
    private TeamModel teamModel;
    private int currentPlayer;
    private List<Bid> biddingHistory=new ArrayList<>();
    private int [] playerOrder;
    BiddingModel(TeamModel teamModel)
    {
        this.teamModel=teamModel;
        this.currentPlayer=new Random().nextInt(4);
        this.playerOrder=teamModel.getPlayerOrder();
    }
    String getCurrentPlayer(){
        return teamModel.getPlayerName(playerOrder[currentPlayer]);
    }
    List<Card> getPlayerCards()
    {
        return teamModel.getPlayerCards();
    }
    private Bid getLastCard()
    {
        Bid card=null;
        for(int i=biddingHistory.size()-1;i>=0;i--)
        {
            if(biddingHistory.get(i).getType()==BidType.CARD)
            {
                card=biddingHistory.get(i);
                break;
            }
        }
        return card;
    }
    private void addBid(Bid bid)
    {
        biddingHistory.add(bid);
        currentPlayer++;
        if(currentPlayer==4)
            currentPlayer=0;
    }
    boolean bid(Bid bid,int user)
    {
        if(user!=playerOrder[currentPlayer])
            return false;
        if(bid.getType()==BidType.CARD)
        {
            Bid lastCard=getLastCard();
            if(lastCard==null)
            {
                addBid(bid);
                return true;
            }
            if(bid.compareTo(lastCard)>0)
            {
                addBid(bid);
                return true;
            }
            else
                return false;
        }
        else if(bid.getType()==BidType.CONTRA)
        {
            if(biddingHistory.size()>0)
            {
                //if previous player bidded with CARD
                if(biddingHistory.get(biddingHistory.size()-1).getType()==BidType.CARD)
                {
                    addBid(bid);
                    return true;
                }
            }
            if(biddingHistory.size()>2)
            {
                //if previous player partner bidded with CARD and our partner didn't bid with CONTRA
                if(biddingHistory.get(biddingHistory.size()-3).getType()==BidType.CARD
                        && biddingHistory.get(biddingHistory.size()-2).getType()!=BidType.CONTRA)
                {
                    addBid(bid);
                    return true;
                }
            }
            return false;
        }
        else if(bid.getType()==BidType.RECONTRA)
        {
            if(biddingHistory.size()>0)
            {
                //if previous player bidded with CONTRA
                if(biddingHistory.get(biddingHistory.size()-1).getType()==BidType.CONTRA)
                {
                    addBid(bid);
                    return true;
                }
            }
            if(biddingHistory.size()>2)
            {
                //if previous player partner bidded with CONTRA and our partner didn't bid with RECONTRA
                if(biddingHistory.get(biddingHistory.size()-3).getType()==BidType.CONTRA
                        && biddingHistory.get(biddingHistory.size()-2).getType()!=BidType.RECONTRA)
                {
                    addBid(bid);
                    return true;
                }
            }
            return false;
        }
        else if(bid.getType()==BidType.PASS)
        {
            //we can always PASS
            addBid(bid);
            if(biddingHistory.size()>3)
            {
                if(biddingHistory.get(biddingHistory.size()-2).getType()==BidType.PASS
                        && biddingHistory.get(biddingHistory.size()-3).getType()==BidType.PASS)
                {
                    //if its a beginning and everyone passed - new Bidding with new cards is started
                    if(biddingHistory.get(biddingHistory.size()-4).getType()==BidType.PASS)
                    {
                        teamModel.changeGameState('B');
                        return true;
                    }
                    //there is a winner
                    else
                    {
                        int curr=currentPlayer;
                        for(int i=biddingHistory.size()-2;i>=0;i--)
                        {
                            curr--;
                            if(curr<0)
                                curr+=4;
                            if(biddingHistory.get(i).getType()==BidType.CARD)
                            {
                                //we will set here biddingWinner and ATU
                                teamModel.setBiddingWinner(playerOrder[curr]);
                                teamModel.setAtu(biddingHistory.get(i).getColor());
                                teamModel.setContract(biddingHistory.get(i).getCount());
                                break;
                            }
                        }
                        teamModel.changeGameState('G');
                        return true;
                    }
                }
            }
            return true;
        }
        return true;
    }
}
