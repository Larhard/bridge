package com.elgassia.bridge.Model;

import java.util.List;

public class ChooseStrategy implements Strategy{
    @SuppressWarnings("unchecked")
    List<Card> [] cardList=new List[4];
    public ChooseStrategy(List<Card>[] cardList) throws IllegalArgumentException
    {
        if(cardList.length!=4)
            throw new IllegalArgumentException();
        for(int i=0;i<4;i++)
        {
            if(cardList[i].size()!=13)
                throw new IllegalArgumentException();
        }
        System.arraycopy(cardList, 0, this.cardList, 0, 4);
    }

    @Override
    public List<Card>[] getCards() {
        return cardList;
    }
}
