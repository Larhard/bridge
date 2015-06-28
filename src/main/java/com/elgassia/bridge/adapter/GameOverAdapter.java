package com.elgassia.bridge.adapter;

import com.elgassia.bridge.Model.Card;

public interface GameOverAdapter {
    int getGameWinner();

    int getTurnsWonByThePlayingTeam();

    Card[][] getPlayedCards();

    int[] getWhoStartedTurn();
}
