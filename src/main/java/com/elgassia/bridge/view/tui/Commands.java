package com.elgassia.bridge.view.tui;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Commands {
    Map<String, Command> commandMap = new HashMap<>();

    public Command parse(String[] cmd) {
        if (commandMap.containsKey(cmd[0])) {
            Command command = commandMap.get(cmd[0]);
            command.setArgs(cmd);
            return command;
        }
        return null;
    }

    public void add(String key, Command command) {
        commandMap.put(key, command);
    }

    public void remove(String key) {
        commandMap.remove(key);
    }

    public Set<String> keySet() {
        return commandMap.keySet();
    }

    public Command get(String key) {
        return commandMap.get(key);
    }

    public void clear() {
        commandMap.clear();
    }
}
