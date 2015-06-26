package com.elgassia.bridge.adapter.main;

import com.elgassia.bridge.adapter.TeamAdapter;

public class LobbyAdapter implements com.elgassia.bridge.adapter.LobbyAdapter {

    private TeamAdapter teamAdapter;

    @Override
    public void init(TeamAdapter teamAdapter) {
        this.teamAdapter = teamAdapter;
    }

    @Override
    public boolean setName(String name) {
        return teamAdapter.getUserTeamModel().getUserLobbyModel().setName(name);
    }
}
