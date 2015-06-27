package com.elgassia.bridge.adapter.main;

import com.elgassia.bridge.Model.Card;
import com.elgassia.bridge.adapter.TeamAdapter;
import com.elgassia.bridge.exception.BridgeLogicException;

import java.util.List;

public class GameAdapter implements com.elgassia.bridge.adapter.GameAdapter {
    private TeamAdapter teamAdapter;

    @Override
    public void init(TeamAdapter teamAdapter) {
        this.teamAdapter = teamAdapter;
    }

    @Override
    public void playCard(Card card) throws BridgeLogicException {
        teamAdapter.getUserTeamModel().getUserGameModel().playCard(card);
    }

    @Override
    public List<Card> getCards() {
        return teamAdapter.getUserTeamModel().getUserGameModel().getMyDeck();
    }

    @Override
    public List<Card> getGrandpasCards() {
        return teamAdapter.getUserTeamModel().getUserGameModel().getGranpasDeck();
    }

    @Override
    public Card[] turnHistory() {
        return teamAdapter.getUserTeamModel().getUserGameModel().getTurnHistory();
    }

    @Override
    public String whoStartedTurn() {
        return teamAdapter.getUserTeamModel().getUserGameModel().whoStartedTurn();
    }
}
