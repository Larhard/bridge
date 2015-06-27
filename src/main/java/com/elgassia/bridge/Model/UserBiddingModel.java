package com.elgassia.bridge.Model;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by vereena on 6/20/15.
 */
public class UserBiddingModel extends Observable implements Observer {
    private int userID;
    private BiddingModel biddingModel;
    private List<Card> userDeck;
    UserBiddingModel(int userID,BiddingModel biddingModel) {
        this.userID = userID;
        this.biddingModel=biddingModel;
        this.userDeck=biddingModel.getPlayerCards(userID);
        biddingModel.addObserver(this);
    }
    List<Card> getMyDeck()
    {
        return userDeck;
    }
    boolean bid(Bid bid)
    {
        if(biddingModel.bid(bid,userID))
        {
            setChanged();
            notifyObservers();
            return true;
        }
        return false;
    }
    List<Bid> getBiddingHistory()
    {
        return biddingModel.getBiddingHistory();
    }
    String getCurrentPlayer()
    {
        return biddingModel.getCurrentPlayer();
    }

    @Override
    public void update(Observable o, Object arg) {
        setChanged();
        notifyObservers();
    }
}
