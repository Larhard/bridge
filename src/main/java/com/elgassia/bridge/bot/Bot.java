package com.elgassia.bridge.bot;

import com.elgassia.bridge.adapter.UserTeamAdapter;

abstract public class Bot implements Runnable {
    private UserTeamAdapter teamAdapter;

    void init(UserTeamAdapter teamAdapter) {
        this.teamAdapter = teamAdapter;
    }
}
