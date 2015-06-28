package com.elgassia.bridge.adapter.main;

import com.elgassia.bridge.Model.Bid;
import com.elgassia.bridge.Model.Card;
import com.elgassia.bridge.Model.UserBiddingModel;
import com.elgassia.bridge.exception.BridgeLogicException;

import java.util.List;

public class BiddingAdapter implements com.elgassia.bridge.adapter.BiddingAdapter {
    private final UserTeamAdapter userTeamAdapter;
    private final UserBiddingModel userBiddingModel;

    public BiddingAdapter(UserTeamAdapter userTeamAdapter, UserBiddingModel userBiddingModel) {
        this.userTeamAdapter = userTeamAdapter;
        this.userBiddingModel = userBiddingModel;
    }

    @Override
    public void bid(Bid bid) throws BridgeLogicException {
        userBiddingModel.bid(bid);
    }

    @Override
    public List<Card> getCards() {
        return userBiddingModel.getMyDeck();
    }

    @Override
    public String getCurrentPlayer() {
        return userBiddingModel.getCurrentPlayer();
    }
}
