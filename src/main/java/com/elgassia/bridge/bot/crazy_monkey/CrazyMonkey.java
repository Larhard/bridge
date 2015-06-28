package com.elgassia.bridge.bot.crazy_monkey;

import com.elgassia.bridge.adapter.UserTeamAdapter;
import com.elgassia.bridge.bot.Bot;

public class CrazyMonkey extends Bot {
    private final UserTeamAdapter currentUserTeamAdapter;

    public CrazyMonkey(UserTeamAdapter currentUserTeamAdapter) {
        this.currentUserTeamAdapter = currentUserTeamAdapter;
    }

    @Override
    public void run() {
        System.out.println("Not Failed!!! :D");
    }
}
