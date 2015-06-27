package com.elgassia.bridge.view.tui.commands.team.lobby;

import com.elgassia.bridge.adapter.LobbyAdapter;
import com.elgassia.bridge.view.tui.Command;

public class SetRandomTeams extends Command {
    private final LobbyAdapter lobbyAdapter;

    public SetRandomTeams(LobbyAdapter lobbyAdapter) {
        this.lobbyAdapter = lobbyAdapter;
    }

    @Override
    public void execute() {
        lobbyAdapter.setRandomTeams();
    }

    @Override
    public String description() {
        return "Set random teams for all players";
    }
}
