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

    @Override
    public String toString() {
        String rank_str = null;
        String color_str = null;

        switch (rank) {
            case TWO:
                rank_str = "2";
                break;
            case THREE:
                rank_str = "3";
                break;
            case FOUR:
                rank_str = "4";
                break;
            case FIVE:
                rank_str = "5";
                break;
            case SIX:
                rank_str = "6";
                break;
            case SEVEN:
                rank_str = "7";
                break;
            case EIGHT:
                rank_str = "8";
                break;
            case NINE:
                rank_str = "9";
                break;
            case TEN:
                rank_str = "10";
                break;
            case JACK:
                rank_str = "J";
                break;
            case QUEEN:
                rank_str = "Q";
                break;
            case KING:
                rank_str = "K";
                break;
            case ACE:
                rank_str = "A";
                break;
        }

        switch (suit) {
            case CLUBS:
                color_str = "C";
                break;
            case DIAMONDS:
                color_str = "D";
                break;
            case HEARTS:
                color_str = "H";
                break;
            case SPADES:
                color_str = "S";
                break;
        }
        return rank_str + color_str;
    }
}
