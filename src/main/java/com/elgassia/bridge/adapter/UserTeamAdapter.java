package com.elgassia.bridge.adapter;

import java.util.Observable;
import java.util.Observer;

public abstract class UserTeamAdapter extends Observable implements Observer {
    public abstract String getName();

    public abstract String getPlayerName(int player);

    public abstract int getPlayerTeam(int player);

    public abstract int[] getPlayersOrder();

    public abstract LobbyAdapter getLobbyAdapter();

    public abstract BiddingAdapter getBiddingAdapter();

    public abstract GameAdapter getGameAdapter();

    public abstract com.elgassia.bridge.adapter.main.TeamAdapter.State getState();

    public abstract int getPlayerId();
}
