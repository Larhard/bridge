package com.elgassia.bridge.Model;

import java.util.List;
import java.util.Observable;

/**
 * Created by vereena on 6/19/15.
 */
public class GameModel extends Observable{
    private TeamModel teamModel;
    private int [] playerOrder;
    private int [] whoStartedTurn=new int[13];
    private Card [][] cardsInTurn=new Card[13][4];
    private int turnCount=0;
    private int playingTeam;
    private Color atu;
    private int contract;
    private int winnerWins=0;
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
    private boolean isAtu(Card.Suit suit)
    {
        if(suit.equals(Card.Suit.CLUBS) && atu.equals(Color.CLUBS))
            return true;
        if(suit.equals(Card.Suit.DIAMONDS) && atu.equals(Color.DIAMONDS))
            return true;
        if(suit.equals(Card.Suit.SPADES) && atu.equals(Color.SPADES))
            return true;
        if(suit.equals(Card.Suit.HEARTS) && atu.equals(Color.HEARTS))
            return true;
        return false;
    }
    private void checkWhoWinTurn()
    {
        //check for the winner - if playingTeam wins winnerWins++
        int tempWinner=0;
        for(int i=1;i<4;i++)
        {
            //if the color is the same
            if(cardsInTurn[turnCount][i].getSuit().equals(cardsInTurn[turnCount][tempWinner].getSuit()))
            {
                //if this Card is bigger than tempWinner card
                if(cardsInTurn[turnCount][i].compareTo(cardsInTurn[turnCount][tempWinner])>0)
                    tempWinner=i;
            }
            //if the color is different but his color is atu
            else if(isAtu(cardsInTurn[turnCount][i].getSuit()))
            {
                tempWinner=i;
            }
        }
        currentPlayer+=tempWinner;
        currentPlayer=currentPlayer%4;
        if(currentPlayer%2==playingTeam)
            winnerWins++;
        //for the next turn (if its not 13) whoStartedTurn and currentPlayer is the winner
        if(turnCount!=12)
        {
            whoStartedTurn[turnCount+1]=currentPlayer;
        }
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
                if(contract<=winnerWins)
                    teamModel.setGameWinner(playingTeam);
                else
                    teamModel.setGameWinner((playingTeam+1)%2);
                teamModel.setHowManyTurnsPlayingTeamWon(winnerWins);
                teamModel.setGameHistory(cardsInTurn,whoStartedTurn);
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
            setChanged();
            notifyObservers();
            return true;
        }

        //check if the Card color is the first Card color
        //if it is - play that Card, changePlayer()  and return true
        if(card.getSuit().equals(cardsInTurn[turnCount][0].getSuit()))
        {
            teamModel.deleteCard(playerOrder[currentPlayer],card);
            cardsInTurn[turnCount][playerCount]=card;
            changePlayer();
            setChanged();
            notifyObservers();
            return true;
        }

        //check if he has Cards with the first Card color
        //if not - play that card and changePlayer() and return true
        if(!teamModel.checkForCardColor(playerOrder[currentPlayer],card.getSuit()))
        {
            teamModel.deleteCard(playerOrder[currentPlayer],card);
            cardsInTurn[turnCount][playerCount]=card;
            changePlayer();
            setChanged();
            notifyObservers();
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
    Card[] getTurnHistory()
    {
        return cardsInTurn[turnCount];
    }
    String whoStartedTurn()
    {
        return teamModel.getPlayerName(playerOrder[whoStartedTurn[turnCount]]);
    }
}
