package com.elgassia.bridge.adapter;

import com.elgassia.bridge.Model.TeamModel;
import com.elgassia.bridge.Model.UserTeamModel;
import com.elgassia.bridge.adapter.main.MainAdapter;

public interface TeamAdapter {
    void init(MainAdapter main_adapter, TeamModel team_model);

    void nextPlayer();

    UserTeamModel getUserTeamModel();

    String getName();

    LobbyAdapter getLobbyAdapter();
}
