package com.elgassia.bridge.adapter.main;

import com.elgassia.bridge.Model.MainModel;
import com.elgassia.bridge.Model.TeamModel;

import java.util.List;
import java.util.Observable;

public class MainAdapter extends com.elgassia.bridge.adapter.Adapter {
    private State state;
    private MainModel main_model;
    private TeamModel team_model;

    public MainAdapter() {
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
    public void new_game() {
        team_model = main_model.newGame();
        assert team_model != null;
        setState(State.LOBBY);
    }

    @Override
    public State getState() {
        return state;
    }

    @Override
    public List<String> getCredits() {
        return main_model.getCredits().getCredits();
    }

    private void setState(State state) {
        this.state = state;
        setChanged();
        notifyObservers();
    }
}
