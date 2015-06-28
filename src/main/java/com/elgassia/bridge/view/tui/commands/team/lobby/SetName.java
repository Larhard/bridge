package com.elgassia.bridge.view.tui.commands.team.lobby;

import com.elgassia.bridge.adapter.LobbyAdapter;
import com.elgassia.bridge.exception.BridgeLogicException;
import com.elgassia.bridge.view.tui.Command;

public class SetName extends Command {

    private final LobbyAdapter lobbyAdapter;

    public SetName(LobbyAdapter lobbyAdapter) {
        this.lobbyAdapter = lobbyAdapter;
    }

    @Override
    public void execute() {
        if (args.length != 2) {
            System.out.println("usage: <new name>");
            return;
        }
        try {
            lobbyAdapter.setName(args[1]);
        } catch (BridgeLogicException e) {
            System.out.println(e.getMessage());
        }
    }
}
