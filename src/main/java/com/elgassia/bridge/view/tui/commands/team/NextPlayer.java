package com.elgassia.bridge.view.tui.commands.team;

import com.elgassia.bridge.adapter.TeamAdapter;
import com.elgassia.bridge.view.tui.Command;
import com.elgassia.bridge.view.tui.scenes.TeamScene;

public class NextPlayer extends Command {
    private final TeamScene teamScene;
    private final com.elgassia.bridge.adapter.TeamAdapter teamAdapter;

    public NextPlayer(TeamScene teamScene, TeamAdapter teamAdapter) {
        this.teamScene = teamScene;
        this.teamAdapter = teamAdapter;
    }

    @Override
    public void execute() {
        teamScene.setCurrentPlayer(teamScene.getCurrentPlayer() + 1);
        teamScene.resetCommands();
    }

    @Override
    public String description() {
        return "Next player";
    }
}
