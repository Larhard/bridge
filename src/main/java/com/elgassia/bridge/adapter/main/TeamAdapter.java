package com.elgassia.bridge.adapter.main;

import com.elgassia.bridge.Model.TeamModel;
import com.elgassia.bridge.Model.UserTeamModel;
import com.elgassia.bridge.adapter.BiddingAdapter;
import com.elgassia.bridge.adapter.GameAdapter;
import com.elgassia.bridge.adapter.LobbyAdapter;

import java.util.Observable;
import java.util.Observer;

public class TeamAdapter extends com.elgassia.bridge.adapter.TeamAdapter implements Observer {
    public enum State {
        LOBBY,
        BIDDING,
        GAME,
    }

    private TeamModel team_model;
    private com.elgassia.bridge.adapter.LobbyAdapter lobby_adapter;
    private BiddingAdapter bidding_adapter;
    private GameAdapter game_adapter;

    int[] players;
    int active_player;
    UserTeamModel[] player_team_models;

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

        lobby_adapter = new com.elgassia.bridge.adapter.main.LobbyAdapter();
        lobby_adapter.init(this);

        bidding_adapter = new com.elgassia.bridge.adapter.main.BiddingAdapter();
        bidding_adapter.init(this);

        game_adapter = new com.elgassia.bridge.adapter.main.GameAdapter();
        game_adapter.init(this);

        assert team_model != null;

        players = team_model.getPlayerOrder();
        active_player = 0;
        player_team_models = new UserTeamModel[players.length];
        for (int i = 0; i < players.length; ++i) {
            player_team_models[i] = new UserTeamModel(players[i], team_model);
        }

        setState(State.LOBBY);
        team_model.addObserver(this);
    }

    @Override
    public void nextPlayer() {
        active_player = (active_player + 1) % players.length;
        setChanged();
        notifyObservers();
    }

    @Override
    public UserTeamModel getUserTeamModel() {
        return player_team_models[active_player];
    }

    @Override
    public String getName() {
        return getUserTeamModel().getPlayerName();
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
    public LobbyAdapter getLobbyAdapter() {
        return lobby_adapter;
    }

    @Override
    public BiddingAdapter getBiddingAdapter() {
        return bidding_adapter;
    }

    @Override
    public GameAdapter getGameAdapter() {
        return game_adapter;
    }

    @Override
    public UserTeamModel[] getUserTeamModels() {
        return player_team_models;
    }

    @Override
    public String getCurrentPlayer() {
        return getUserTeamModel().getUserBiddingModel().getCurrentPlayer();
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
            }
        }
    }
}
