package com.elgassia.bridge.adapter.main;

import com.elgassia.bridge.adapter.TeamAdapter;

public class BiddingAdapter implements com.elgassia.bridge.adapter.BiddingAdapter {
    private TeamAdapter teamAdapter;

    @Override
    public void init(TeamAdapter teamAdapter) {
        this.teamAdapter = teamAdapter;
    }
}
