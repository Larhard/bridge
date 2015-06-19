package com.elgassia.bridge.view.tui.commands;

import com.elgassia.bridge.view.tui.Command;
import com.elgassia.bridge.view.tui.View;

public class Exit extends Command {
    private final View view;

    public Exit(View view) {
        this.view = view;
    }

    @Override
    public void execute() {
        if (args.length != 1) {
            System.out.println("What did you mean saying that?");
            return;
        }

        view.exit();
    }

    @Override
    public String description() {
        return "Exit program";
    }
}
