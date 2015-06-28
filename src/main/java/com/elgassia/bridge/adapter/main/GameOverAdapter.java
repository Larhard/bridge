package com.elgassia.bridge.adapter.main;

import com.elgassia.bridge.Model.Card;
import com.elgassia.bridge.Model.GameOverModel;

public class GameOverAdapter implements com.elgassia.bridge.adapter.GameOverAdapter {
    private final GameOverModel gameOverModel;

    public GameOverAdapter(GameOverModel gameOverModel) {
        this.gameOverModel = gameOverModel;
    }

    @Override
    public int getGameWinner() {
        return gameOverModel.getGameWinner();
    }

    @Override
    public int getTurnsWonByThePlayingTeam() {
        return gameOverModel.getTurnsWonByThePlayingTeam();
    }

    @Override
    public Card[][] getPlayedCards() {
        return gameOverModel.getPlayedCards();
    }

    @Override
    public int[] getWhoStartedTurn() {
        return gameOverModel.getWhoStartedTurn();
    }
}
