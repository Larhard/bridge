package com.elgassia.bridge.view.tui.commands.team;

import com.elgassia.bridge.adapter.Adapter;
import com.elgassia.bridge.adapter.main.TeamAdapter;
import com.elgassia.bridge.view.tui.Command;

public class NextPlayer extends Command {
    private final com.elgassia.bridge.adapter.TeamAdapter teamAdapter;

    public NextPlayer(com.elgassia.bridge.adapter.TeamAdapter teamAdapter) {

        this.teamAdapter = teamAdapter;
    }

    @Override
    public void execute() {
        teamAdapter.nextPlayer();
        System.out.println("Player: " + teamAdapter.getName());
    }

    @Override
    public String description() {
        return "Next player";
    }
}
