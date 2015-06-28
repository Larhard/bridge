package com.elgassia.bridge.adapter;

public interface UserTeamAdapter {
    String getName();

    String getPlayerName(int player);

    int getPlayerTeam(int player);

    int[] getPlayersOrder();

    LobbyAdapter getLobbyAdapter();

    BiddingAdapter getBiddingAdapter();

    GameAdapter getGameAdapter();

    com.elgassia.bridge.adapter.main.TeamAdapter.State getState();

    int getPlayerId();
}
