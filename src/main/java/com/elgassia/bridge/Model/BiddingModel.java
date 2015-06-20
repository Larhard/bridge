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
    private int firstBidder;
    List<Bid> biddingHistory=new ArrayList<>();
    BiddingModel(TeamModel teamModel)
    {
        this.teamModel=teamModel;
        this.currentPlayer=new Random().nextInt(4);
        this.firstBidder=this.currentPlayer;
    }
    String getCurrentPlayer(){
        return teamModel.getPlayerName(currentPlayer);
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
        if(user!=currentPlayer)
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

        }
        else if(bid.getType()==BidType.RECONTRA)
        {

        }
        else if(bid.getType()==BidType.PASS)
        {

        }
        return true;
    }
}
