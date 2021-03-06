package com.elgassia.bridge.Model;

import com.elgassia.bridge.exception.BridgeLogicException;

import java.io.*;
import java.util.List;
import java.util.Observable;

public class TeamModel extends Observable implements Serializable{
    private char state;
    private String players[]=new String[4];
    private int playerTeam[]=new int[4];
    private int playerOrder[]=new int[4];
    private int biddingWinnerOrder;
    private Strategy strategy=new RandomStrategy();
    @SuppressWarnings("unchecked")
    private List<Card>[] cardLists = new List[4];
    private Color atu;
    private int contract;
    //these are set after the last game turn
    private int gameWinner; //which team won the game
    private int turnsWonByThePlayingTeam;
    private int [] whoStartedTurn=new int[13];
    private Card [][] cardsInTurn=new Card[13][4];
    private LobbyModel lobbyModel;
    private BiddingModel biddingModel;
    private GameModel gameModel;
    private GameOverModel gameOverModel;
    private Character previousState;

    TeamModel()
    {
        players[0]="user1";
        players[1]="user2";
        players[2]="user3";
        players[3]="user4";
        for(int i=0;i<4;i++)
            playerTeam[i]=-1;
        playerOrder[0]=-1;
        changeGameState('L');
    }
    LobbyModel getLobbyModel()
    {
        return lobbyModel;
    }
    BiddingModel getBiddingModel()
    {
        return biddingModel;
    }
    GameModel getGameModel()
    {
        return gameModel;
    }
    public GameOverModel getGameOverModel()
    {
        return gameOverModel;
    }
    void changeGameState(char state)
    {
        if (previousState != null) {
            state = previousState;
        }
        if (this.state != state) {
            this.state = state;

            switch (state) {
                case 'L':
                    if (lobbyModel == null) {
                        lobbyModel = new LobbyModel(this);
                    }
                    break;

                case 'B':
                    if (biddingModel == null) {
                        biddingModel = new BiddingModel(this);
                    }
                    break;

                case 'G':
                    if (gameModel == null) {
                        gameModel = new GameModel(this);
                    }
                    break;

                case 'O':
                    if (gameOverModel == null) {
                        gameOverModel = new GameOverModel(this);
                    }
                    break;
            }

            setChanged();
            notifyObservers();
        }
    }

    /**
     * State can be:
     *   'L' - lobby
     *   'B' - bidding
     *   'G' - game
     *   'O' - game over
     */
    public char getState() {
        return state;
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
        if(team>1 || team<0)
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
        throw new BridgeLogicException("You can't be in a team which already has two players");
    }
    public int getUserTeam(int user)
    {
        return playerTeam[user];
    }
    public String getPlayerName(int user)
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
        setChanged();
        notifyObservers();
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
            setChanged();
            notifyObservers();
        }
        return this.playerOrder;
    }
    void setBiddingWinner(int winner)
    {
        biddingWinnerOrder=winner;
        setChanged();
        notifyObservers();
    }
    void setAtu(Color atu)
    {
        this.atu=atu;
        setChanged();
        notifyObservers();
    }
    void setContract(int contract)
    {
        this.contract=contract;
        setChanged();
        notifyObservers();
    }
    int getBiddingWinner()
    {
        return biddingWinnerOrder;
    }
    public Color getAtu()
    {
        return atu;
    }
    public int getContract()
    {
        return contract;
    }
    void chooseDeckStrategy(Strategy strategy)
    {
        this.strategy=strategy;
        setChanged();
        notifyObservers();
    }
    void deleteCard(int user,Card card)
    {
        cardLists[user].remove(card);
        setChanged();
        notifyObservers();
    }
    boolean checkForCard(int user,Card card)
    {
        return cardLists[user].contains(card);
    }
    boolean checkForCardColor(int user,Card.Suit suit)
    {
        for(Card card:cardLists[user])
        {
            if(card.getSuit().equals(suit))
                return true;
        }
        return false;
    }
    void setGameWinner(int winner)
    {
        this.gameWinner=playerTeam[playerOrder[winner]];
        setChanged();
        notifyObservers();
    }
    void setHowManyTurnsPlayingTeamWon(int winnerWins)
    {
        this.turnsWonByThePlayingTeam=winnerWins;
        setChanged();
        notifyObservers();
    }
    void setGameHistory(Card[][]cardsInTurn,int []whoStartedTurn)
    {
        this.cardsInTurn=cardsInTurn;
        this.whoStartedTurn=whoStartedTurn;
        //turn it to actual indexes
        for(int i=0;i<13;i++)
            whoStartedTurn[i]=playerOrder[whoStartedTurn[i]];
        setChanged();
        notifyObservers();
    }
    int getGameWinner()
    {
        return this.gameWinner;
    }
    int getTurnsWonByThePlayingTeam()
    {
        return this.turnsWonByThePlayingTeam;
    }
    Card[][] getPlayedCards()
    {
        return cardsInTurn;
    }
    int[] getWhoStartedTurn()
    {
        return whoStartedTurn;
    }
    void somethingChanged()
    {
        setChanged();
        notifyObservers();
    }
    public Memento saveToMemento() throws IOException {
        return new Memento(this);
    }
    public static class Memento {
        byte [] teamModel;
        public Memento(byte[] teamModel)
        {
            this.teamModel=teamModel;
        }
        Memento(TeamModel teamModel)throws IOException
        {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                ObjectOutput objectOutput = null;
            try {
                objectOutput = new ObjectOutputStream(byteArrayOutputStream);
                objectOutput.writeObject(teamModel);
                this.teamModel = byteArrayOutputStream.toByteArray();
            }
            finally {
                if (objectOutput != null) {
                    try {
                        objectOutput.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        public byte [] getBytes()
        {
            return teamModel;
        }
        public TeamModel restore() throws IOException,ClassNotFoundException{
            ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(this.teamModel);
            ObjectInput objectInput=null;
            TeamModel teamModel1=null;
            try {
                objectInput = new ObjectInputStream(byteArrayInputStream);
                teamModel1 = (TeamModel) objectInput.readObject();
            }
            finally {
                if(objectInput!=null) {
                    try {
                        objectInput.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    byteArrayInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            teamModel1.returnToLobby();
            return teamModel1;
        }
    }

    private void returnToLobby() {
        char previousState = getState();
        changeGameState('L');
        if (this.previousState == null) {
            this.previousState = previousState;
        }
        lobbyModel.resetPlayerStates();
    }
}
