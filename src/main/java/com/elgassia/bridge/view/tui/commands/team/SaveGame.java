package com.elgassia.bridge.view.tui.commands.team;

import com.elgassia.bridge.adapter.TeamAdapter;
import com.elgassia.bridge.view.tui.Command;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by vereena on 6/28/15.
 */
public class SaveGame extends Command {

    private final com.elgassia.bridge.adapter.TeamAdapter teamAdapter;

    public SaveGame(TeamAdapter teamAdapter) {
        this.teamAdapter = teamAdapter;
    }

    @Override
    public void execute() {
        if (args.length != 2) {
            System.out.println("usage: <file name>");
            return;
        }
        try {
            File file=new File(args[1]);
            FileOutputStream out=new FileOutputStream(file);
            teamAdapter.saveGame(out);
            out.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
