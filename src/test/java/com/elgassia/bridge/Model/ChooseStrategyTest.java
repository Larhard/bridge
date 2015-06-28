package com.elgassia.bridge.Model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static junit.framework.Assert.assertEquals;

public class ChooseStrategyTest {

    @Test
    public void testGetCards() throws Exception {
        List<Card> [] cardlist=new List[4];
        for(int i=0;i<4;i++)
            cardlist[i]=new ArrayList<>();
        Random random=new Random();
        int temp;
        for(Card.Rank rank: Card.Rank.values())
        {
            for(Card.Suit suit: Card.Suit.values())
            {
                temp=random.nextInt(4);
                while(cardlist[temp].size()==13)
                {
                    temp=random.nextInt(4);
                }
                cardlist[temp].add(new Card(rank,suit));
            }
        }
        ChooseStrategy x= new ChooseStrategy(cardlist);
        assertEquals("getCards failed",x.getCards().length,4);
        assertEquals("getCards failed",x.getCards()[0].size(),13);
        assertEquals("getCards failed",x.getCards()[1].size(),13);
        assertEquals("getCards failed",x.getCards()[2].size(),13);
        assertEquals("getCards failed",x.getCards()[3].size(),13);
    }
}