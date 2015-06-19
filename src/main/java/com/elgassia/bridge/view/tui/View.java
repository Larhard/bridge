package com.elgassia.bridge.view.tui;

import com.elgassia.bridge.adapter.Adapter;
import com.elgassia.bridge.view.tui.commands.Exit;
import com.elgassia.bridge.view.tui.commands.Help;

import java.util.Observable;
import java.util.Scanner;

public class View implements com.elgassia.bridge.view.View {
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
                String[] line = scanner.nextLine().split("\\s+");
                Command command = commands.parse(line);

                if (command != null) {
                    command.execute();
                } else {
                    System.out.println("Unknown command '" + line[0] + "'");
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
        adapter.addObserver(this);
        commands.add("help", new Help(commands));
        commands.add("exit", new Exit(this));
        readerThread = new ReaderThread(this);
        readerThread.start();
    }

    public void exit() {
        readerThread.exit();
    }

    @Override
    public void update(Observable observable, Object o) {

    }
}
