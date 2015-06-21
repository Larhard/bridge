package com.elgassia.bridge.adapter;

import com.elgassia.bridge.Model.MainModel;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

abstract public class Adapter extends Observable implements Observer {
    public abstract void init(MainModel main_model);

    public abstract void newGame();

    public enum State {
        MAIN_MENU,
        LOBBY,
    }

    abstract public TeamAdapter getTeamAdapter();

    public abstract State getState();

    public abstract List<String> getCredits();
}
