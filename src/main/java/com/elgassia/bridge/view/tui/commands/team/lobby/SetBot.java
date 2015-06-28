package com.elgassia.bridge.view.tui.commands.team.lobby;

import com.elgassia.bridge.adapter.UserTeamAdapter;
import com.elgassia.bridge.bot.crazy_monkey.CrazyMonkey;
import com.elgassia.bridge.view.tui.Command;
import com.elgassia.bridge.view.tui.scenes.TeamScene;

public class SetBot extends Command {
    private final TeamScene teamScene;
    private final UserTeamAdapter currentUserTeamAdapter;
    private final int currentPlayer;

    public SetBot(TeamScene teamScene, UserTeamAdapter currentUserTeamAdapter, int currentPlayer) {
        this.teamScene = teamScene;
        this.currentUserTeamAdapter = currentUserTeamAdapter;
        this.currentPlayer = currentPlayer;
    }

    @Override
    public void execute() {
        teamScene.setBot(currentPlayer, new CrazyMonkey(currentUserTeamAdapter));
    }
}
