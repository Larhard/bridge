package com.elgassia.bridge.Model;

/**
 * Created by vereena on 6/20/15.
 */
public class Card implements Comparable<Card> {

    public static enum Rank {
        TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE;
    }
    public static enum Suit {
        SPADES, HEARTS, DIAMONDS, CLUBS;
    }
    private Rank rank;
    private Suit suit;

    Card(Rank rank,Suit suit)
    {
        this.rank=rank;
        this.suit=suit;
    }
    Rank getRank()
    {
        return this.rank;
    }
    Suit getSuit()
    {
        return this.suit;
    }
    @Override
    public int compareTo(Card o) {
        if(this.suit.compareTo(o.getSuit())!=0)
            return this.suit.compareTo(o.getSuit());
        return this.rank.compareTo(o.getRank());
    }

    @Override
    public boolean equals(Object obj) {
        if(obj.getClass().equals(Card.class))
        {
            Card x=(Card)obj;
            if(!this.rank.equals(x.getRank()))
                return false;
            if(!this.suit.equals(x.getSuit()))
                return false;
            return true;
        }
        return false;
    }
}
