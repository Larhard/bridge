package com.elgassia.bridge.view.tui.scenes;

import com.elgassia.bridge.view.tui.Commands;
import com.elgassia.bridge.view.tui.Scene;
import com.elgassia.bridge.view.tui.View;
import com.elgassia.bridge.view.tui.commands.NextPlayer;
import com.elgassia.bridge.view.tui.commands.SetName;
import com.elgassia.bridge.view.tui.commands.StartGame;

public class LobbyScene extends Scene {
    @Override
    protected void prepareCommands(Commands commands, View view) {
        super.prepareCommands(commands, view);
        commands.add("next", new NextPlayer(view.getAdapter().getTeamAdapter()));
        commands.add("set_name", new SetName(view.getAdapter().getTeamAdapter().getLobbyAdapter()));
        commands.add("start", new StartGame(view.getAdapter().getTeamAdapter().getLobbyAdapter()));
        System.out.println("Player: " + view.getAdapter().getTeamAdapter().getName());
    }
}
