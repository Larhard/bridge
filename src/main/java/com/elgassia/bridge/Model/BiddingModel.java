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
    boolean bid(Bid bid,int user)
    {
        if(user!=currentPlayer)
            return false;
        if(bid.getType()==BidType.CARD)
        {

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
