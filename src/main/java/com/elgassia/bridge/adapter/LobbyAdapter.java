package com.elgassia.bridge.adapter;

import com.elgassia.bridge.exception.BridgeLogicException;

public interface LobbyAdapter {
    void init(TeamAdapter teamAdapter);

    boolean setName(String name);

    void startGame() throws BridgeLogicException;

    void setRandomTeams();

    void setTeam(int team) throws BridgeLogicException;
}
