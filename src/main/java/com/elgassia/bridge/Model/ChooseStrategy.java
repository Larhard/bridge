package com.elgassia.bridge.Model;

import com.elgassia.bridge.exception.BridgeLogicException;

import java.util.HashSet;
import java.util.List;

public class ChooseStrategy implements Strategy{
    @SuppressWarnings("unchecked")
    List<Card> [] cardList=new List[4];
    ChooseStrategy(List<Card> [] cardList) throws IllegalArgumentException, BridgeLogicException {
        if(cardList.length!=4)
            throw new BridgeLogicException("You should choose all four decks");
        for(int i=0;i<4;i++)
        {
            if(cardList[i].size()!=13)
                throw new BridgeLogicException("All decks should have size of 13");
        }
        HashSet<Card> test=new HashSet<Card>();
        for(int i=0;i<4;i++)
        {
            for(Card x:cardList[i])
            {
                test.add(x);
            }
        }
        if(test.size()!=52)
            throw new BridgeLogicException("Two players cannot have the same card");
        System.arraycopy(cardList, 0, this.cardList, 0, 4);
    }

    @Override
    public List<Card>[] getCards() {
        return cardList;
    }
}
