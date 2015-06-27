package com.elgassia.bridge.view.tui.scenes;

import com.elgassia.bridge.view.tui.Commands;
import com.elgassia.bridge.view.tui.Scene;
import com.elgassia.bridge.view.tui.View;
import com.elgassia.bridge.view.tui.commands.main.Credits;
import com.elgassia.bridge.view.tui.commands.main.NewGame;

public class MainScene extends Scene {
    @Override
    protected void prepareCommands(Commands commands, View view) {
        super.prepareCommands(commands, view);
        commands.add("credits", new Credits(view.getAdapter()));
        commands.add("new_game", new NewGame(view.getAdapter()));
    }
}
