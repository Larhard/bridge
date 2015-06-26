package com.elgassia.bridge.view.tui;

import com.elgassia.bridge.view.tui.commands.Exit;
import com.elgassia.bridge.view.tui.commands.Help;

abstract public class Scene {
    public void init(View view) {
        Commands commands = view.getCommands();
        commands.clear();
        prepareCommands(commands, view);
    }

    protected void prepareCommands(Commands commands, View view) {
        commands.add("help", new Help(commands));
        commands.add("exit", new Exit(view));
    }
}
