package com.elgassia.bridge.adapter.main;

import com.elgassia.bridge.Model.Color;
import com.elgassia.bridge.Model.TeamModel;
import com.elgassia.bridge.Model.UserTeamModel;
import com.elgassia.bridge.adapter.GameOverAdapter;
import com.elgassia.bridge.adapter.UserTeamAdapter;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class TeamAdapter extends com.elgassia.bridge.adapter.TeamAdapter implements Observer {
    public enum State {
        LOBBY,
        BIDDING,
        GAME,
        OVER,
    }

    private TeamModel team_model;

    Map<Integer, UserTeamAdapter> playerTeamAdapters = new HashMap<>();

    private State state;

    public void setState(State state) {
        if (!state.equals(this.state)) {
            this.state = state;
            setChanged();
            notifyObservers();
        }
    }

    @Override
    public State getState() {
        return state;
    }

    @Override
    public void init(MainAdapter main_adapter, TeamModel team_model) {
        this.team_model = team_model;


        assert team_model != null;

        int[] players = team_model.getPlayerOrder();
        for (int player : players) {
            playerTeamAdapters.put(player, new com.elgassia.bridge.adapter.main.UserTeamAdapter(player, this, new UserTeamModel(player, team_model)));
        }

        setState(State.LOBBY);
        team_model.addObserver(this);
    }

    @Override
    public String getPlayerName(int player) {
        return team_model.getPlayerName(player);
    }

    @Override
    public int getPlayerTeam(int player) {
        return team_model.getUserTeam(player);
    }

    @Override
    public int[] getPlayersOrder() {
        return team_model.getPlayerOrder();
    }

    @Override
    public UserTeamAdapter getPlayerTeamAdapter(int playerId) {
        return playerTeamAdapters.get(playerId);
    }

    @Override
    public GameOverAdapter getGameOverAdapter() {
        return new com.elgassia.bridge.adapter.main.GameOverAdapter(team_model.getGameOverModel());
    }

    @Override
    public Color getAtu() {
        return team_model.getAtu();
    }

    @Override
    public int getContract() {
        return team_model.getContract();
    }

    @Override
    public void update(Observable observable, Object o) {
        if (observable == team_model) {
            switch (team_model.getState()) {
                case 'L':
                    setState(State.LOBBY);
                    break;
                case 'B':
                    setState(State.BIDDING);
                    break;
                case 'G':
                    setState(State.GAME);
                    break;
                case 'O':
                    setState(State.OVER);
                    break;
                default:
                    assert false;
            }
        }
    }
}
