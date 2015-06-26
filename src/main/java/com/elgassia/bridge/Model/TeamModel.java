package com.elgassia.bridge.Model;

import com.elgassia.bridge.exception.BridgeLogicException;

import java.util.List;
import java.util.Observable;

/**
 * Created by vereena on 6/19/15.
 */
public class TeamModel extends Observable{
    private char state;
    private String players[]=new String[4];
    private int playerTeam[]=new int[4];
    private int playerOrder[]=new int[4];
    private int biddingWinnerOrder;
    private Strategy strategy=new RandomStrategy();
    private List<Card>[] cardLists = new List[4];
    private Color atu;
    private int contract;
    TeamModel()
    {
        players[0]="user1";
        players[1]="user2";
        players[2]="user3";
        players[3]="user4";
        for(int i=0;i<4;i++)
            playerTeam[i]=-1;
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
    boolean setTeam(int user,int team) throws BridgeLogicException
    {
        if(team>2)
            throw new BridgeLogicException("Invalid team number");
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
    List<Card> getPlayerCards(int user)
    {
        return cardLists[user];
    }
    void drawCards()
    {
        DeckBuilder builder=new DeckBuilder();
        cardLists=builder.build(strategy);
    }
    public int [] getPlayerOrder()
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
    void setBiddingWinner(int winner)
    {
        biddingWinnerOrder=winner;
    }
    void setAtu(Color atu)
    {
        this.atu=atu;
    }
    void setContract(int contract)
    {
        this.contract=contract;
    }
    int getBiddingWinner()
    {
        return biddingWinnerOrder;
    }
    Color getAtu()
    {
        return atu;
    }
    int getContract()
    {
        return contract;
    }
    void chooseDeckStrategy(Strategy strategy)
    {
        this.strategy=strategy;
    }
    boolean deleteCard(int user,Card card)
    {
        return cardLists[user].remove(card);
    }
}
