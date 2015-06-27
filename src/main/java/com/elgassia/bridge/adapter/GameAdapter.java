package com.elgassia.bridge.adapter;

import com.elgassia.bridge.Model.Card;
import com.elgassia.bridge.exception.BridgeLogicException;

import java.util.List;

public interface GameAdapter {
    void init(TeamAdapter teamAdapter);

    void playCard(Card card) throws BridgeLogicException;

    List<Card> getCards();

    List<Card> getGrandpasCards();
}
