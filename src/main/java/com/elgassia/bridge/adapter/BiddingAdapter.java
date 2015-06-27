package com.elgassia.bridge.adapter;

import com.elgassia.bridge.Model.Bid;
import com.elgassia.bridge.Model.Card;
import com.elgassia.bridge.exception.BridgeLogicException;

import java.util.List;

public interface BiddingAdapter {
    void init(TeamAdapter teamAdapter);

    void bid(Bid bid) throws BridgeLogicException;

    List<Card> getCards();

    public abstract String getCurrentPlayer();
}
