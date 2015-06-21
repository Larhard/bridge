package com.elgassia.bridge.view.tui.commands;

import com.elgassia.bridge.adapter.Adapter;
import com.elgassia.bridge.view.tui.Command;

public class Credits extends Command {
    private final Adapter adapter;

    public Credits(Adapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public void execute() {
        adapter.getCredits().forEach(System.out::println);
    }
}
