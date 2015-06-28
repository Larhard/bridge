package com.elgassia.bridge.adapter;

import com.elgassia.bridge.Model.Card;
import com.elgassia.bridge.exception.BridgeLogicException;

import java.util.List;

public interface GameAdapter {
    void playCard(Card card) throws BridgeLogicException;

    List<Card> getCards();

    List<Card> getGrandpasCards();

    Card[] turnHistory();

    String whoStartedTurn();
}
