package com.elgassia.bridge.adapter;

import com.elgassia.bridge.Model.Bid;

public interface BiddingAdapter {
    void init(TeamAdapter teamAdapter);

    void bid(Bid bid);
}
