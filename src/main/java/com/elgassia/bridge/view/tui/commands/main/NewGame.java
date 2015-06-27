package com.elgassia.bridge.view.tui.commands.main;

import com.elgassia.bridge.adapter.Adapter;
import com.elgassia.bridge.view.tui.Command;

public class NewGame extends Command {
    private final Adapter adapter;

    public NewGame(Adapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public void execute() {
        adapter.newGame();
    }
}
