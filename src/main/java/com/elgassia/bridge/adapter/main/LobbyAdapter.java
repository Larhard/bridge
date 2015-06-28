package com.elgassia.bridge.adapter.main;

import com.elgassia.bridge.Model.UserLobbyModel;
import com.elgassia.bridge.exception.BridgeLogicException;

public class LobbyAdapter implements com.elgassia.bridge.adapter.LobbyAdapter {

    private final UserTeamAdapter userTeamAdapter;
    private final UserLobbyModel userLobbyModel;

    public LobbyAdapter(UserTeamAdapter userTeamAdapter, UserLobbyModel userLobbyModel) {
        this.userTeamAdapter = userTeamAdapter;
        this.userLobbyModel = userLobbyModel;
    }

    @Override
    public boolean setName(String name) throws BridgeLogicException {
        return userLobbyModel.setName(name);
    }

    @Override
    public void startGame() throws BridgeLogicException {
        userLobbyModel.ready();
    }

    @Override
    public void setRandomTeam() throws BridgeLogicException {
        userLobbyModel.randomTeam();
    }

    @Override
    public void setTeam(int team) throws BridgeLogicException {
        userLobbyModel.setTeam(team);
    }
}
