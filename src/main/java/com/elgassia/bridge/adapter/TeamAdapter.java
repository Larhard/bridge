package com.elgassia.bridge.adapter;

import com.elgassia.bridge.Model.Color;
import com.elgassia.bridge.Model.TeamModel;
import com.elgassia.bridge.adapter.main.MainAdapter;

import java.util.Observable;

abstract public class TeamAdapter extends Observable {
    public abstract com.elgassia.bridge.adapter.main.TeamAdapter.State getState();

    public abstract void init(MainAdapter main_adapter, TeamModel team_model);

    public abstract String getPlayerName(int player);

    public abstract int getPlayerTeam(int player);

    public abstract int[] getPlayersOrder();

    public abstract UserTeamAdapter getPlayerTeamAdapter(int playerId);

    public abstract GameOverAdapter getGameOverAdapter();

    public abstract Color getAtu();

    public abstract int getContract();
}
