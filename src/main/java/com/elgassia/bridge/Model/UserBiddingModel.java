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
        this.userDeck=biddingModel.getPlayerCards();
    }
}
