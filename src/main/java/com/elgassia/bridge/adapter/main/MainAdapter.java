package com.elgassia.bridge.adapter.main;

import com.elgassia.bridge.Model.MainModel;
import com.elgassia.bridge.Model.TeamModel;
import com.elgassia.bridge.Model.UserTeamModel;

import java.util.List;
import java.util.Observable;

public class MainAdapter extends com.elgassia.bridge.adapter.Adapter {
    private State state;
    private MainModel main_model;
    private TeamModel team_model;

    int[] players;
    int active_player;
    UserTeamModel[] player_team_models;

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
        players = team_model.getPlayerOrder();
        active_player = 0;
        player_team_models = new UserTeamModel[players.length];
        for (int i = 0; i < players.length; ++i) {
            player_team_models[i] = new UserTeamModel(players[i], team_model);
        }
    }

    @Override
    public void nextPlayer() {
        active_player = (active_player + 1) % players.length;
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
