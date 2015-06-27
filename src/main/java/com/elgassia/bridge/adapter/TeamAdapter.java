package com.elgassia.bridge.adapter;

import com.elgassia.bridge.Model.TeamModel;
import com.elgassia.bridge.Model.UserTeamModel;
import com.elgassia.bridge.adapter.main.MainAdapter;

import java.util.Observable;

abstract public class TeamAdapter extends Observable {
    public abstract com.elgassia.bridge.adapter.main.TeamAdapter.State getState();

    public abstract void init(MainAdapter main_adapter, TeamModel team_model);

    public abstract void nextPlayer();

    public abstract UserTeamModel getUserTeamModel();

    public abstract String getName();

    public abstract LobbyAdapter getLobbyAdapter();

    public abstract BiddingAdapter getBiddingAdapter();

    public abstract GameAdapter getGameAdapter();

    public abstract UserTeamModel[] getUserTeamModels();
}
