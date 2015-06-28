package com.elgassia.bridge.view.tui.commands.team.lobby;

import com.elgassia.bridge.adapter.LobbyAdapter;
import com.elgassia.bridge.exception.BridgeLogicException;
import com.elgassia.bridge.view.tui.Command;

import java.util.List;

public class StartGame extends Command{

    private final List<LobbyAdapter> lobbyAdapters;

    public StartGame(List<LobbyAdapter> lobbyAdapters) {
        this.lobbyAdapters = lobbyAdapters;
    }

    @Override
    public void execute() {
        try {
            for (LobbyAdapter adapter : lobbyAdapters) {
                adapter.startGame();
            }
        } catch (BridgeLogicException e) {
            System.out.println(e.getMessage());
        }
    }
}
