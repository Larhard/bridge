package com.elgassia.bridge.Model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DeckBuilderTest {

    @Test
    public void testBuild() throws Exception {
        Strategy y=mock(Strategy.class);
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
        when(y.getCards()).thenReturn(cardlist);
        DeckBuilder x=new DeckBuilder();
        List<Card> [] a=x.build(y);
        assertEquals("build failed",a.length,4);
        assertEquals("build failed",a[0].size(),13);
        assertEquals("build failed",a[1].size(),13);
        assertEquals("build failed",a[2].size(),13);
        assertEquals("build failed",a[3].size(),13);
    }
}