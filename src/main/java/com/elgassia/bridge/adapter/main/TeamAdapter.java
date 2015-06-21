package com.elgassia.bridge.adapter.main;

import com.elgassia.bridge.Model.TeamModel;
import com.elgassia.bridge.Model.UserTeamModel;
import com.elgassia.bridge.adapter.Adapter;

public class TeamAdapter implements com.elgassia.bridge.adapter.TeamAdapter {
    private MainAdapter main_adapter;
    private TeamModel team_model;

    int[] players;
    int active_player;
    UserTeamModel[] player_team_models;

    @Override
    public void init(MainAdapter main_adapter, TeamModel team_model) {
        this.main_adapter = main_adapter;
        this.team_model = team_model;

        assert team_model != null;

        players = team_model.getPlayerOrder();
        active_player = 0;
        player_team_models = new UserTeamModel[players.length];
        for (int i = 0; i < players.length; ++i) {
            player_team_models[i] = new UserTeamModel(players[i], team_model);
        }

        main_adapter.setState(Adapter.State.LOBBY);
    }

    @Override
    public void nextPlayer() {
        active_player = (active_player + 1) % players.length;
    }

    private UserTeamModel getUserTeamModel() {
        return player_team_models[active_player];
    }

    @Override
    public String getName() {
        return getUserTeamModel().getPlayerName();
    }
}
