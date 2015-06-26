package com.elgassia.bridge.adapter.main;

import com.elgassia.bridge.Model.TeamModel;
import com.elgassia.bridge.Model.UserTeamModel;
import com.elgassia.bridge.adapter.LobbyAdapter;

public class TeamAdapter implements com.elgassia.bridge.adapter.TeamAdapter {
    private MainAdapter main_adapter;
    private TeamModel team_model;
    private com.elgassia.bridge.adapter.LobbyAdapter lobby_adapter;

    int[] players;
    int active_player;
    UserTeamModel[] player_team_models;

    @Override
    public void init(MainAdapter main_adapter, TeamModel team_model) {
        this.main_adapter = main_adapter;
        this.team_model = team_model;

        lobby_adapter = new com.elgassia.bridge.adapter.main.LobbyAdapter();
        lobby_adapter.init(this);

        assert team_model != null;

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
    public UserTeamModel getUserTeamModel() {
        return player_team_models[active_player];
    }

    @Override
    public String getName() {
        return getUserTeamModel().getPlayerName();
    }

    @Override
    public LobbyAdapter getLobbyAdapter() {
        return lobby_adapter;
    }

    @Override
    public UserTeamModel[] getUserTeamModels() {
        return player_team_models;
    }
}
