package com.elgassia.bridge.adapter.main;

import com.elgassia.bridge.Model.UserTeamModel;
import com.elgassia.bridge.adapter.BiddingAdapter;
import com.elgassia.bridge.adapter.GameAdapter;
import com.elgassia.bridge.adapter.LobbyAdapter;

public class UserTeamAdapter implements com.elgassia.bridge.adapter.UserTeamAdapter {
    private int playerId;
    private com.elgassia.bridge.adapter.TeamAdapter teamAdapter;
    private UserTeamModel userTeamModel;
    private GameAdapter game_adapter;
    private BiddingAdapter bidding_adapter;
    private LobbyAdapter lobby_adapter;

    public UserTeamAdapter(int playerId, com.elgassia.bridge.adapter.TeamAdapter teamAdapter, UserTeamModel userTeamModel) {
        this.playerId = playerId;
        this.teamAdapter = teamAdapter;
        this.userTeamModel = userTeamModel;

        game_adapter = new com.elgassia.bridge.adapter.main.GameAdapter(this, userTeamModel.getUserGameModel());

        lobby_adapter = new com.elgassia.bridge.adapter.main.LobbyAdapter(this, userTeamModel.getUserLobbyModel());

        bidding_adapter = new com.elgassia.bridge.adapter.main.BiddingAdapter(this, userTeamModel.getUserBiddingModel());
    }

    @Override
    public String getName() {
        return getPlayerName(playerId);
    }

    @Override
    public String getPlayerName(int player) {
        return teamAdapter.getPlayerName(player);
    }

    @Override
    public int getPlayerTeam(int player) {
        return teamAdapter.getPlayerTeam(player);
    }

    @Override
    public int[] getPlayersOrder() {
        return teamAdapter.getPlayersOrder();
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
    public TeamAdapter.State getState() {
        return teamAdapter.getState();
    }

    @Override
    public int getPlayerId() {
        return playerId;
    }
}
