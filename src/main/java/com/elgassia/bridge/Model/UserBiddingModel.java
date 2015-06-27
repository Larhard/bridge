package com.elgassia.bridge.Model;

import java.util.List;

/**
 * Created by vereena on 6/20/15.
 */
public class UserBiddingModel {
    private int userID;
    private BiddingModel biddingModel;
    private List<Card> userDeck;
    UserBiddingModel(int userID,BiddingModel biddingModel) {
        this.userID = userID;
        this.biddingModel=biddingModel;
        this.userDeck=biddingModel.getPlayerCards(userID);
    }
    List<Card> getMyDeck()
    {
        return userDeck;
    }
    boolean bid(Bid bid)
    {
        return biddingModel.bid(bid,userID);
    }
    List<Bid> getBiddingHistory()
    {
        return biddingModel.getBiddingHistory();
    }
    String getCurrentPlayer()
    {
        return biddingModel.getCurrentPlayer();
    }
}
