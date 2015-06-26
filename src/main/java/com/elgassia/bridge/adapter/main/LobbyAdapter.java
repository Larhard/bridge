package com.elgassia.bridge.adapter.main;

import com.elgassia.bridge.Model.UserTeamModel;
import com.elgassia.bridge.adapter.TeamAdapter;
import com.elgassia.bridge.exception.BridgeLogicException;

public class LobbyAdapter implements com.elgassia.bridge.adapter.LobbyAdapter {

    private TeamAdapter teamAdapter;

    @Override
    public void init(TeamAdapter teamAdapter) {
        this.teamAdapter = teamAdapter;
    }

    @Override
    public boolean setName(String name) {
        return teamAdapter.getUserTeamModel().getUserLobbyModel().setName(name);
    }

    @Override
    public void startGame() throws BridgeLogicException {
        for (UserTeamModel model : teamAdapter.getUserTeamModels()) {
            if (! model.getUserLobbyModel().ready()) {
                throw new BridgeLogicException("player " + model.getPlayerName() + " can not be ready yet");
            }
        }
    }

    @Override
    public void setRandomTeams() {
        for (UserTeamModel model : teamAdapter.getUserTeamModels()) {
            model.getUserLobbyModel().randomTeam();
        }
    }
}
