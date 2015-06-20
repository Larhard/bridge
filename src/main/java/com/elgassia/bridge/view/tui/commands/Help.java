package com.elgassia.bridge.view.tui.commands;

import com.elgassia.bridge.view.tui.Command;
import com.elgassia.bridge.view.tui.Commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Help extends Command {
    private final Commands commands;

    public Help(Commands commands) {
        this.commands = commands;
    }

    @Override
    public void execute() {
        if (args.length != 1) {
            System.out.println("Don't know what to do with additional arguments");
            return;
        }

        System.out.println("Bridge");
        System.out.println();
        System.out.println("Help:");

        List<String> commandsList = new ArrayList<>(commands.keySet());
        Collections.sort(commandsList);

        for (String command : commandsList) {
            StringBuilder cmdString = new StringBuilder();
            cmdString.append("  ");
            cmdString.append(command);

            String desc = commands.get(command).description();
            if (desc != null) {
                cmdString.append(" - ");
                cmdString.append(desc);
            }

            System.out.println(cmdString.toString());
        }
    }

    @Override
    public String description() {
        return "Show this message";
    }
}
