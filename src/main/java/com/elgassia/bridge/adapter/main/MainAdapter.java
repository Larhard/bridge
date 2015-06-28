package com.elgassia.bridge.adapter.main;

import com.elgassia.bridge.Model.MainModel;
import com.elgassia.bridge.Model.TeamModel;

import java.io.FileInputStream;
import java.io.IOException;
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
        if (observable == team_adapter) {
            setChanged();
            notifyObservers();
        }
    }

    @Override
    public void init(MainModel main_model) {

        this.main_model = main_model;
    }

    @Override
    public void newGame() {
        team_adapter = new TeamAdapter();
        team_adapter.init(this, main_model.newGame());

        setState(State.TEAM);
    }

    @Override
    public void loadGame(FileInputStream in){
        try {
            byte[] bytes = new byte[2 << 10];
            in.read(bytes);
            TeamModel.Memento memento = new TeamModel.Memento(bytes);
            team_adapter = new TeamAdapter();
            team_adapter.init(this, main_model.newGame(memento));

            setState(State.TEAM);
        }catch (IOException e)
        {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
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
