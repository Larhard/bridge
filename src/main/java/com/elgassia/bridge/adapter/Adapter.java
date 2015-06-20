package com.elgassia.bridge.adapter;

import java.util.Observable;
import java.util.Observer;

abstract public class Adapter extends Observable implements Observer {
    public enum State {
        MAIN_MENU,
    }

    public abstract State getState();
}
