package com.elgassia.bridge.Model;

import java.util.List;
import java.util.Random;

/**
 * Created by vereena on 6/19/15.
 */
public class BiddingModel {
    private TeamModel teamModel;
    private int currentPlayer;
    BiddingModel(TeamModel teamModel)
    {
        this.teamModel=teamModel;
        this.currentPlayer=new Random().nextInt(4);
    }
    String getCurrentPlayer(){
        return teamModel.getPlayerName(currentPlayer);
    }
    List<Card> getPlayerCards()
    {
        return teamModel.getPlayerCards();
    }
}
