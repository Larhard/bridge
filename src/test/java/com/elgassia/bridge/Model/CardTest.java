package com.elgassia.bridge.Model;

import org.junit.Test;
import static junit.framework.Assert.assertEquals;

public class CardTest {

    @Test
    public void testGetRank() throws Exception {
        Card x=new Card(Card.Rank.ACE, Card.Suit.HEARTS);
        assertEquals("getRank failed",x.getRank(), Card.Rank.ACE);
        x=new Card(Card.Rank.FOUR, Card.Suit.SPADES);
        assertEquals("getRank failed",x.getRank(), Card.Rank.FOUR);
        x=new Card(Card.Rank.KING, Card.Suit.DIAMONDS);
        assertEquals("getRank failed",x.getRank(), Card.Rank.KING);
        x=new Card(Card.Rank.SEVEN, Card.Suit.CLUBS);
        assertEquals("getRank failed",x.getRank(), Card.Rank.SEVEN);
    }

    @Test
    public void testGetSuit() throws Exception {
        Card x=new Card(Card.Rank.ACE, Card.Suit.HEARTS);
        assertEquals("getSuit failed",x.getSuit(), Card.Suit.HEARTS);
        x=new Card(Card.Rank.FOUR, Card.Suit.SPADES);
        assertEquals("getSuit failed",x.getSuit(), Card.Suit.SPADES);
        x=new Card(Card.Rank.KING, Card.Suit.DIAMONDS);
        assertEquals("getSuit failed",x.getSuit(), Card.Suit.DIAMONDS);
        x=new Card(Card.Rank.SEVEN, Card.Suit.CLUBS);
        assertEquals("getSuit failed",x.getSuit(), Card.Suit.CLUBS);
    }

    @Test
    public void testCompareTo() throws Exception {
        assertEquals("compareTo failed",new Card(Card.Rank.SEVEN, Card.Suit.HEARTS).compareTo(new Card(Card.Rank.SEVEN, Card.Suit.HEARTS)),0);
        assertEquals("compareTo failed",new Card(Card.Rank.KING, Card.Suit.HEARTS).compareTo(new Card(Card.Rank.SEVEN, Card.Suit.HEARTS)),6);
        assertEquals("compareTo failed",new Card(Card.Rank.SEVEN, Card.Suit.HEARTS).compareTo(new Card(Card.Rank.SEVEN, Card.Suit.CLUBS)),-2);
        assertEquals("compareTo failed",new Card(Card.Rank.SEVEN, Card.Suit.HEARTS).compareTo(null),-1);
    }

    @Test
    public void testEquals() throws Exception {
        assertEquals("equals failed",new Card(Card.Rank.SEVEN, Card.Suit.HEARTS).equals(new Card(Card.Rank.SEVEN, Card.Suit.HEARTS)),true);
        assertEquals("equals failed",new Card(Card.Rank.KING, Card.Suit.HEARTS).equals(new Card(Card.Rank.SEVEN, Card.Suit.HEARTS)),false);
        assertEquals("equals failed",new Card(Card.Rank.SEVEN, Card.Suit.HEARTS).equals(new Card(Card.Rank.SEVEN, Card.Suit.CLUBS)),false);
        assertEquals("equals failed",new Card(Card.Rank.SEVEN, Card.Suit.HEARTS).equals(null),false);
    }
}