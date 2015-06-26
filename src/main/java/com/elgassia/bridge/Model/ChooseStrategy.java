package com.elgassia.bridge.Model;

import java.util.List;

/**
 * Created by vereena on 6/26/15.
 */
public class ChooseStrategy implements Strategy{
    List<Card> [] cardList=new List[4];
    ChooseStrategy(List<Card> [] cardList) throws IllegalArgumentException
    {
        if(cardList.length!=4)
            throw new IllegalArgumentException();
        for(int i=0;i<4;i++)
        {
            this.cardList[i]=cardList[i];
        }
    }

    @Override
    public List<Card>[] getCards() {
        return cardList;
    }
}
