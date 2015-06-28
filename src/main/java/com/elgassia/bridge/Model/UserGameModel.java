package com.elgassia.bridge.Model;

import com.elgassia.bridge.exception.BridgeLogicException;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by vereena on 6/26/15.
 */
public class UserGameModel extends Observable implements Observer{
    private int userID;
    private GameModel gameModel;
    UserGameModel(int userID,GameModel gameModel) {
        this.userID = userID;
        this.gameModel=gameModel;
    }
    public boolean playCard(Card card) throws BridgeLogicException {
        if(gameModel.playCard(userID,card))
        {
            setChanged();
            notifyObservers();
            return true;
        }
        return false;
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
    Card[] getPreviousTurnHistory() throws BridgeLogicException { return gameModel.getPreviousTurnHistory();}
    String whoStartedTurn()
    {
        return gameModel.whoStartedTurn();
    }
    int getCurrentPlayerID(){ return  gameModel.getCurrentPlayerID();}
    int getCurrentTurnNumber() { return gameModel.getCurrentTurnNumber();}
    int getHowManyTurnsWereWonBy(int team) throws BridgeLogicException {
        return gameModel.getHowManyTurnsWereWonBy(team);
    }
    @Override
    public void update(Observable o, Object arg) {
        setChanged();
        notifyObservers();
    }
}
