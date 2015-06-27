package com.elgassia.bridge.view.tui.commands.team.lobby;

import com.elgassia.bridge.adapter.LobbyAdapter;
import com.elgassia.bridge.exception.BridgeLogicException;
import com.elgassia.bridge.view.tui.Command;

public class SetRandomTeams extends Command {
    private final LobbyAdapter lobbyAdapter;

    public SetRandomTeams(LobbyAdapter lobbyAdapter) {
        this.lobbyAdapter = lobbyAdapter;
    }

    @Override
    public void execute() {
        try {
            lobbyAdapter.setRandomTeams();
        }catch (BridgeLogicException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String description() {
        return "Set random teams for all players";
    }
}
