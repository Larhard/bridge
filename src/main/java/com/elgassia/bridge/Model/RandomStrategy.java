package com.elgassia.bridge.Model;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by vereena on 6/26/15.
 */
public class RandomStrategy implements Strategy {
    @Override
    public List<Card>[] getCards() {
        List<Card>[] cardLists = new List[4];
        cardLists[0]=new LinkedList<>();
        cardLists[1]=new LinkedList<>();
        cardLists[2]=new LinkedList<>();
        cardLists[3]=new LinkedList<>();
        Random random=new Random();
        int temp;
        for(Card.Rank rank: Card.Rank.values())
        {
            for(Card.Suit suit: Card.Suit.values())
            {
                temp=random.nextInt(4);
                while(cardLists[temp].size()==13)
                {
                    temp=random.nextInt(4);
                }
                cardLists[temp].add(new Card(rank,suit));
            }
        }
        return cardLists;
    }
}
