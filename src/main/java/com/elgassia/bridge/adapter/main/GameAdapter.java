package com.elgassia.bridge.adapter.main;

import com.elgassia.bridge.Model.Card;
import com.elgassia.bridge.adapter.TeamAdapter;
import com.elgassia.bridge.exception.BridgeLogicException;

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
}
