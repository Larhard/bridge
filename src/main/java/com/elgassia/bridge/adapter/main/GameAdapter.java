package com.elgassia.bridge.adapter.main;

import com.elgassia.bridge.Model.Card;
import com.elgassia.bridge.Model.UserGameModel;
import com.elgassia.bridge.exception.BridgeLogicException;

import java.util.List;

public class GameAdapter implements com.elgassia.bridge.adapter.GameAdapter {
    private final UserTeamAdapter userTeamAdapter;
    private final UserGameModel userGameModel;

    public GameAdapter(UserTeamAdapter userTeamAdapter, UserGameModel userGameModel) {
        this.userTeamAdapter = userTeamAdapter;
        this.userGameModel = userGameModel;
    }

    @Override
    public void playCard(Card card) throws BridgeLogicException {
        userGameModel.playCard(card);
    }

    @Override
    public List<Card> getCards() {
        return userGameModel.getMyDeck();
    }

    @Override
    public List<Card> getGrandpasCards() {
        return userGameModel.getGranpasDeck();
    }

    @Override
    public Card[] turnHistory() {
        return userGameModel.getTurnHistory();
    }

    @Override
    public Card[] previousTurnHistory() throws BridgeLogicException {
        return userGameModel.getPreviousTurnHistory();
    }

    @Override
    public String whoStartedTurn() {
        return userGameModel.whoStartedTurn();
    }

    @Override
    public String whoStartedPreviousTurn() throws BridgeLogicException {
        return userGameModel.whoStartedPreviousTurn();
    }
}
