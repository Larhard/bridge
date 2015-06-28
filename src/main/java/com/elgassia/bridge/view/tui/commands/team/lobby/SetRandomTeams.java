package com.elgassia.bridge.view.tui.commands.team.lobby;

import com.elgassia.bridge.adapter.LobbyAdapter;
import com.elgassia.bridge.exception.BridgeLogicException;
import com.elgassia.bridge.view.tui.Command;

import java.util.List;

public class SetRandomTeams extends Command {
    private final List<LobbyAdapter> lobbyAdapters;

    public SetRandomTeams(List<LobbyAdapter> lobbyAdapters) {
        this.lobbyAdapters = lobbyAdapters;
    }

    @Override
    public void execute() {
        try {
            for (LobbyAdapter adapter : lobbyAdapters) {
                adapter.setRandomTeam();
            }
        }catch (BridgeLogicException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String description() {
        return "Set random teams for all players";
    }
}
