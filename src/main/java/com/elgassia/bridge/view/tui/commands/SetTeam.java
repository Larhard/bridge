package com.elgassia.bridge.view.tui.commands;

import com.elgassia.bridge.adapter.LobbyAdapter;
import com.elgassia.bridge.exception.BridgeLogicException;
import com.elgassia.bridge.view.tui.Command;

public class SetTeam extends Command {
    private final LobbyAdapter lobbyAdapter;

    public SetTeam(LobbyAdapter lobbyAdapter) {
        this.lobbyAdapter = lobbyAdapter;
    }

    private void usage() {
        System.out.println("usage: <team id>");
    }

    @Override
    public void execute() {
        if (args.length != 2) {
            usage();
            return;
        }

        try {
            lobbyAdapter.setTeam(Integer.parseInt(args[1]));
        } catch (BridgeLogicException e) {
            System.out.println(e.getMessage());
        }
    }
}
