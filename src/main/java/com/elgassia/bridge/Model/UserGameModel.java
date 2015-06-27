package com.elgassia.bridge.Model;

import java.util.List;

/**
 * Created by vereena on 6/26/15.
 */
public class UserGameModel {
    private int userID;
    private GameModel gameModel;
    UserGameModel(int userID,GameModel gameModel) {
        this.userID = userID;
        this.gameModel=gameModel;
    }
    boolean playCard(Card card)
    {
        return gameModel.playCard(userID,card);
    }
    List<Card> getGranpasDeck()
    {
        return gameModel.getGrandpasDeck();
    }
    List<Card> getMyDeck()
    {
        return gameModel.getUserDeck(userID);
    }
    Card[] getTurnHistory()
    {
        return gameModel.getTurnHistory();
    }
    String whoStartedTurn()
    {
        return gameModel.whoStartedTurn();
    }
}
