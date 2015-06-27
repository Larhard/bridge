package com.elgassia.bridge.Model;

import com.elgassia.bridge.exception.BridgeLogicException;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class UserBiddingModel extends Observable implements Observer {
    private int userID;
    private BiddingModel biddingModel;
    UserBiddingModel(int userID,BiddingModel biddingModel) {
        this.userID = userID;
        this.biddingModel=biddingModel;
        biddingModel.addObserver(this);
    }
    public List<Card> getMyDeck()
    {
        return biddingModel.getPlayerCards(userID);
    }
    public boolean bid(Bid bid) throws BridgeLogicException {
        if(biddingModel.bid(bid,userID))
        {
            setChanged();
            notifyObservers();
            return true;
        }
        return false;
    }
    public List<Bid> getBiddingHistory()
    {
        return biddingModel.getBiddingHistory();
    }
    public String getCurrentPlayer()
    {
        return biddingModel.getCurrentPlayer();
    }
    public int getCurrentPlayerID(){
        return biddingModel.getCurrentPlayerID();
    }

    @Override
    public void update(Observable o, Object arg) {
        setChanged();
        notifyObservers();
    }
}
