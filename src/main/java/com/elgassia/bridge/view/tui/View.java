package com.elgassia.bridge.view.tui;

import com.elgassia.bridge.adapter.Adapter;
import com.elgassia.bridge.view.tui.scenes.LobbyScene;
import com.elgassia.bridge.view.tui.scenes.MainScene;

import java.util.EnumMap;
import java.util.Observable;
import java.util.Scanner;

public class View implements com.elgassia.bridge.view.View {
    private Scene scene;
    private Adapter adapter;

    private EnumMap<Adapter.State, Class<? extends Scene>> state_map = new EnumMap<>(Adapter.State.class);

    private class ReaderThread extends Thread {
        private final View view;
        private boolean running;
        private Scanner scanner = new Scanner(System.in);

        ReaderThread(View view) {
            this.view = view;
        }

        @Override
        public void run() {
            running = true;
            while (running) {
                if (scanner.hasNextLine()) {
                    String[] line = scanner.nextLine().split("\\s+");
                    Command command = commands.parse(line);

                    if (command != null) {
                        command.execute();
                    } else {
                        System.out.println("Unknown command '" + line[0] + "'");
                    }
                } else {
                    exit();
                }
            }
        }

        public void exit() {
            running = false;
        }
    }

    Commands commands = new Commands();
    ReaderThread readerThread;

    @Override
    public void init(Adapter adapter) {
        state_map.put(Adapter.State.MAIN_MENU, MainScene.class);
        state_map.put(Adapter.State.TEAM, LobbyScene.class);

        this.adapter = adapter;
        adapter.addObserver(this);

        setScene(state_map.get(Adapter.State.MAIN_MENU));

        readerThread = new ReaderThread(this);
        readerThread.start();
    }

    private void setScene(Class<? extends Scene> scene_class) {
        try {
            scene = scene_class.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        scene.init(this);
    }

    public void exit() {
        readerThread.exit();
    }

    public Commands getCommands() {
        return commands;
    }

    public Adapter getAdapter() {
        return adapter;
    }

    @Override
    public void update(Observable observable, Object o) {
        if (observable == adapter) {
            Class<? extends Scene> new_scene = state_map.get(adapter.getState());
            assert new_scene != null;
            if (! scene.getClass().equals(new_scene)) {
                setScene(new_scene);
            }
        }
    }
}
