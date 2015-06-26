package com.elgassia.bridge.view.tui.commands;

import com.elgassia.bridge.adapter.LobbyAdapter;
import com.elgassia.bridge.exception.BridgeLogicException;
import com.elgassia.bridge.view.tui.Command;

public class StartGame extends Command{
    private final LobbyAdapter lobbyAdapter;

    public StartGame(LobbyAdapter lobbyAdapter) {
        this.lobbyAdapter = lobbyAdapter;
    }

    @Override
    public void execute() {
        try {
            lobbyAdapter.startGame();
        } catch (BridgeLogicException e) {
            System.out.println(e.getMessage());
        }
    }
}
