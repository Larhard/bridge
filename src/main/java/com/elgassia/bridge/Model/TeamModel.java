package com.elgassia.bridge.Model;

import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Random;

/**
 * Created by vereena on 6/19/15.
 */
public class TeamModel extends Observable{
    private char state;
    private String players[]=new String[4];
    private int playerTeam[]=new int[4];
    private int playerOrder[]=new int[4];
    private List<Card> deck=new LinkedList<>();
    TeamModel()
    {
        players[0]="user1";
        players[1]="user2";
        players[2]="user3";
        players[3]="user4";
        for(int i=0;i<4;i++)
            playerTeam[i]=-1;
        for(Card.Rank rank: Card.Rank.values())
        {
            for(Card.Suit suit: Card.Suit.values())
            {
                deck.add(new Card(rank,suit));
            }
        }
        playerOrder[0]=-1;
        state='0';
    }
    LobbyModel getLobbyModel()
    {
        return new LobbyModel(this);
    }
    BiddingModel getBiddingModel()
    {
        return new BiddingModel(this);
    }
    GameModel getGameModel()
    {
        return new GameModel(this);
    }
    GameOverModel getGameOverModel()
    {
        return new GameOverModel(this);
    }
    void changeGameState(char state)
    {
        this.state=state;
        setChanged();
        notifyObservers();
    }
    void setName(int user,String name)
    {
        players[user]=name;
        setChanged();
        notifyObservers();
    }
    private int countTeamPlayers(int team)
    {
        int counter=0;
        for (int i=0;i<4;i++)
        {
            if (playerTeam[i]==team)
                counter++;
        }
        return counter;
    }
    boolean setTeam(int user,int team)
    {
        if(playerTeam[user]==team)
            return true;
        if(countTeamPlayers(team)<2)
        {
            playerTeam[user]=team;
            setChanged();
            notifyObservers();
            return true;
        }
        return false;
    }
    int getUserTeam(int user)
    {
        return playerTeam[user];
    }
    String getPlayerName(int user)
    {
        return players[user];
    }
    List<Card> getPlayerCards()
    {
        List<Card> cardList=new LinkedList<>();
        int k;
        for(int i=0;i<13;i++)
        {
            k=new Random().nextInt(this.deck.size());
            cardList.add(this.deck.get(k));
            this.deck.remove(k);
        }
        return cardList;
    }
    int [] getPlayerOrder()
    {
        if(playerOrder[0]==-1)
        {
            playerOrder[0]=0;
            if(playerTeam[0]==playerTeam[1])
            {
                playerOrder[2]=1;
                playerOrder[1]=2;
                playerOrder[3]=3;
            }
            else if(playerTeam[0]==playerTeam[2])
            {
                playerOrder[2]=2;
                playerOrder[1]=1;
                playerOrder[3]=3;
            }
            if(playerTeam[0]==playerTeam[3])
            {
                playerOrder[2]=3;
                playerOrder[1]=1;
                playerOrder[3]=2;
            }
        }
        return this.playerOrder;
    }
}
