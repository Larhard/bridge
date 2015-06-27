package com.elgassia.bridge.adapter;

import com.elgassia.bridge.exception.BridgeLogicException;

public interface LobbyAdapter {
    void init(TeamAdapter teamAdapter);

    boolean setName(String name) throws BridgeLogicException;

    void startGame() throws BridgeLogicException;

    void setRandomTeams() throws BridgeLogicException;

    void setRandomTeam() throws BridgeLogicException;

    void setTeam(int team) throws BridgeLogicException;
}
