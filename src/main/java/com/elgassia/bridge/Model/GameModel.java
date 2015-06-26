package com.elgassia.bridge.Model;

import java.util.List;

/**
 * Created by vereena on 6/19/15.
 */
public class GameModel {
    private TeamModel teamModel;
    private int [] playerOrder;
    private int playingTeam;
    private Color atu;
    private int contract;
    private int currentPlayer;
    private int grandpa;
    GameModel(TeamModel teamModel)
    {
        this.teamModel=teamModel;
        this.playerOrder=teamModel.getPlayerOrder();
        this.playingTeam=teamModel.getBiddingWinner()%2;
        this.atu=teamModel.getAtu();
        this.contract=teamModel.getContract();
        this.currentPlayer=teamModel.getBiddingWinner()+1;
        if(this.currentPlayer==4)
            this.currentPlayer=0;
        this.grandpa=this.currentPlayer+1;
        if(this.grandpa==4)
            this.grandpa=0;
    }
    private void changePlayer()
    {
        currentPlayer++;
        if(currentPlayer==4)
            currentPlayer=0;
    }
    boolean playCard(int user,Card card)
    {
        if(currentPlayer==grandpa)
        {
            //if we are the bidding winner and its grandma turn
            if(user==playerOrder[(grandpa+2)%4])
            {
                //delete Card from deck
                if(teamModel.deleteCard(playerOrder[currentPlayer],card)) {
                    changePlayer();
                    return true;
                }
                //if there was no such card
                return false;
            }
            else
                return false;
        }
        if(user!=playerOrder[currentPlayer])
            return false;
        if(teamModel.deleteCard(playerOrder[currentPlayer],card)) {
            changePlayer();
            return true;
        }
        return false;
    }
    List<Card> getGrandpasDeck()
    {
        return teamModel.getPlayerCards(playerOrder[grandpa]);
    }
    List<Card> getUserDeck(int user)
    {
        return teamModel.getPlayerCards(user);
    }
}
