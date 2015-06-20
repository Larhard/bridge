package com.elgassia.bridge.adapter.main;

import java.util.Observable;

public class Adapter extends com.elgassia.bridge.adapter.Adapter {
    private State state;

    public Adapter() {
        state = State.MAIN_MENU;
    }

    @Override
    public void update(Observable observable, Object o) {

    }

    @Override
    public State getState() {
        return state;
    }
}
