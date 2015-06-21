package com.elgassia.bridge.adapter;

import com.elgassia.bridge.Model.MainModel;

import java.util.Observable;
import java.util.Observer;

abstract public class Adapter extends Observable implements Observer {
    public abstract void init(MainModel main_model);

    public enum State {
        MAIN_MENU,
    }

    public abstract State getState();
}
