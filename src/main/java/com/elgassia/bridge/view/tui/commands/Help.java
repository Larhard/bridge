package com.elgassia.bridge.view.tui.commands;

import com.elgassia.bridge.view.tui.Command;

public class Help extends Command {
    @Override
    public void execute() {
        if (args.length != 1) {
            System.out.println("Don't know what to do with additional arguments");
            return;
        }

        System.out.println("Bridge");
        System.out.println();
        System.out.println("Help:");
        System.out.println("  help");
        System.out.println("  exit");
    }
}
