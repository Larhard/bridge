package com.elgassia.bridge.adapter.main;

import com.elgassia.bridge.Model.Bid;
import com.elgassia.bridge.adapter.TeamAdapter;
import com.elgassia.bridge.exception.BridgeLogicException;

public class BiddingAdapter implements com.elgassia.bridge.adapter.BiddingAdapter {
    private TeamAdapter teamAdapter;

    @Override
    public void init(TeamAdapter teamAdapter) {
        this.teamAdapter = teamAdapter;
    }

    @Override
    public void bid(Bid bid) throws BridgeLogicException {
        teamAdapter.getUserTeamModel().getUserBiddingModel().bid(bid);
    }
}
