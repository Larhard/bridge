package com.elgassia.bridge.adapter.main;

import com.elgassia.bridge.Model.MainModel;

import java.util.List;
import java.util.Observable;

public class MainAdapter extends com.elgassia.bridge.adapter.Adapter {
    private State state;
    private MainModel main_model;
    private TeamAdapter team_adapter;

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
    public void newGame() {
        team_adapter = new TeamAdapter();
        team_adapter.init(this, main_model.newGame());
    }

    @Override
    public com.elgassia.bridge.adapter.TeamAdapter getTeamAdapter() {
        return team_adapter;
    }

    @Override
    public State getState() {
        return state;
    }

    @Override
    public List<String> getCredits() {
        return main_model.getCredits().getCredits();
    }

    protected void setState(State state) {
        this.state = state;
        setChanged();
        notifyObservers();
    }
}
