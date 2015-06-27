package com.elgassia.bridge.Model;

public class Card implements Comparable<Card> {

    public enum Rank {
        TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE
    }
    public enum Suit {
        SPADES, HEARTS, DIAMONDS, CLUBS
    }
    private Rank rank;
    private Suit suit;

    public Card(Rank rank, Suit suit)
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
        if(o==null)
            return -1;
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
