package com.elgassia.bridge.adapter;

import com.elgassia.bridge.exception.BridgeLogicException;

public interface LobbyAdapter {
    boolean setName(String name) throws BridgeLogicException;

    void startGame() throws BridgeLogicException;

    void setRandomTeam() throws BridgeLogicException;

    void setTeam(int team) throws BridgeLogicException;

    String getName();
}
