package com.elgassia.bridge.view.tui.commands.main;

import com.elgassia.bridge.adapter.Adapter;
import com.elgassia.bridge.view.tui.Command;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by vereena on 6/28/15.
 */
public class LoadGame extends Command {

    private final Adapter adapter;

    public LoadGame(Adapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public void execute() {
        if (args.length != 2) {
            System.out.println("usage: <file name>");
            return;
        }
        try {
            FileInputStream in=new FileInputStream(args[1]);
            adapter.loadGame(in);
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
