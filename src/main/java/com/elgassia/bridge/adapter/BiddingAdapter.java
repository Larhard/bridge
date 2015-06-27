package com.elgassia.bridge.adapter;

import com.elgassia.bridge.Model.Bid;
import com.elgassia.bridge.exception.BridgeLogicException;

public interface BiddingAdapter {
    void init(TeamAdapter teamAdapter);

    void bid(Bid bid) throws BridgeLogicException;
}
