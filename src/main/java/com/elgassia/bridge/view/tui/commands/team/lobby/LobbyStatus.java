package com.elgassia.bridge.view.tui.commands.team.lobby;

import com.elgassia.bridge.adapter.TeamAdapter;
import com.elgassia.bridge.view.tui.Command;

public class LobbyStatus extends Command {
    private final TeamAdapter teamAdapter;

    public LobbyStatus(TeamAdapter teamAdapter) {
        this.teamAdapter = teamAdapter;
    }

    @Override
    public void execute() {
        System.out.println("  === Lobby ===");
        System.out.println();
        System.out.println("Players / teams:");
        for (int player : teamAdapter.getPlayersOrder()) {
            System.out.println("  " + teamAdapter.getPlayerName(player) + " / " + teamAdapter.getPlayerTeam(player));
        }
    }
}
