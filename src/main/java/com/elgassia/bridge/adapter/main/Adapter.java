package com.elgassia.bridge.adapter.main;

import com.elgassia.bridge.Model.MainModel;

import java.util.List;
import java.util.Observable;

public class Adapter extends com.elgassia.bridge.adapter.Adapter {
    private State state;
    private MainModel main_model;

    public Adapter() {
        state = State.MAIN_MENU;
    }

    @Override
    public void update(Observable observable, Object o) {

    }

    @Override
    public void init(MainModel main_model) {

        this.main_model = main_model;
    }

    @Override
    public State getState() {
        return state;
    }
}
