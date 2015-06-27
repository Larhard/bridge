package com.elgassia.bridge.Model;

import java.util.List;

/**
 * Created by vereena on 6/19/15.
 */
public class GameModel {
    private TeamModel teamModel;
    private int [] playerOrder;
    private int [] whoStartedTurn=new int[13];
    private Card [][] cardsInTurn=new Card[13][4];
    private int turnCount=0;
    private int playingTeam;
    private Color atu;
    private int contract;
    private int winnersWins=0;
    private int playerCount=0;
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
        this.whoStartedTurn[0]=this.currentPlayer;
    }
    private void checkWhoWinTurn()
    {
        //check for the winner - if playingTeam wins winnerWins++
        //for the next turn (if its not 13) whoStartedTurn is the winner
    }
    private void changePlayer()
    {
        //(current++, playerCount++, maybe turnCount++ and check for the end)
        currentPlayer++;
        if(currentPlayer==4)
            currentPlayer=0;
        playerCount++;
        if(playerCount==4)
        {
            checkWhoWinTurn();
            playerCount=0;
            turnCount++;
            if(turnCount==13)
            {
                //Game Over
                teamModel.changeGameState('O');
            }
        }
    }
    private boolean tryToPlay(Card card)
    {
        //check if currentPlayer has that Card
        //if not return false
        if(!teamModel.checkForCard(playerOrder[currentPlayer],card))
            return false;

        //check if he is first player
        //if he is - play that Card, changePlayer() and return true
        if(playerCount==0)
        {
            teamModel.deleteCard(playerOrder[currentPlayer],card);
            cardsInTurn[turnCount][playerCount]=card;
            changePlayer();
            return true;
        }

        //check if the Card color is the first Card color
        //if it is - play that Card, changePlayer()  and return true
        if(card.getSuit().equals(cardsInTurn[turnCount][0].getSuit()))
        {
            teamModel.deleteCard(playerOrder[currentPlayer],card);
            cardsInTurn[turnCount][playerCount]=card;
            changePlayer();
            return true;
        }

        //check if he has Cards with the first Card color
        //if not - play that card and changePlayer() and return true
        if(!teamModel.checkForCardColor(playerOrder[currentPlayer],card.getSuit()))
        {
            teamModel.deleteCard(playerOrder[currentPlayer],card);
            cardsInTurn[turnCount][playerCount]=card;
            changePlayer();
            return true;
        }

        return false;
        //return false
    }
    boolean playCard(int user,Card card)
    {
        //check if its our turn
        if(currentPlayer==grandpa)
        {
            //if we are the bidding winner and its grandpas turn
            if(user==playerOrder[(grandpa+2)%4])
            {
                return tryToPlay(card);
            }
            else
                return false;
        }
        if(user!=playerOrder[currentPlayer])
            return false;
        return tryToPlay(card);
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
