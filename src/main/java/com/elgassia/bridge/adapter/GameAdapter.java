package com.elgassia.bridge.adapter;

import com.elgassia.bridge.Model.Card;
import com.elgassia.bridge.exception.BridgeLogicException;

public interface GameAdapter {
    void init(TeamAdapter teamAdapter);

    void playCard(Card card) throws BridgeLogicException;
}
