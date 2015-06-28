package com.elgassia.bridge.Model;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class BidTest {

    @Test
    public void testGetType() throws Exception {
        Bid x=new Bid(BidType.PASS);
        assertEquals("getType failed",BidType.PASS,x.getType());
        x=new Bid(BidType.CONTRA);
        assertEquals("getType failed",BidType.CONTRA,x.getType());
        x=new Bid(BidType.RECONTRA);
        assertEquals("getType failed",BidType.RECONTRA,x.getType());
        x=new Bid(BidType.CARD,1,Color.SPADES);
        assertEquals("getType failed",BidType.CARD,x.getType());
    }

    @Test
    public void testGetCount() throws Exception {
        Bid x=new Bid(BidType.PASS);
        assertEquals("getCount failed",null,x.getCount());
        x=new Bid(BidType.CONTRA);
        assertEquals("getCount failed",null,x.getCount());
        x=new Bid(BidType.RECONTRA);
        assertEquals("getCount failed",null,x.getCount());
        x=new Bid(BidType.CARD,5,Color.SPADES);
        assertEquals("getCount failed",new Integer(5),x.getCount());
    }

    @Test
    public void testGetColor() throws Exception {
        Bid x=new Bid(BidType.PASS);
        assertEquals("getColor failed",null,x.getColor());
        x=new Bid(BidType.CONTRA);
        assertEquals("getColor failed",null,x.getColor());
        x=new Bid(BidType.RECONTRA);
        assertEquals("getColor failed",null,x.getColor());
        x=new Bid(BidType.CARD,5,Color.SPADES);
        assertEquals("getColor failed",Color.SPADES,x.getColor());
    }

    @Test
    public void testCompareTo() throws Exception {
        assertEquals("compareTo failed",new Bid(BidType.CARD,5,Color.CLUBS).compareTo(new Bid(BidType.CARD,5,Color.CLUBS)),0);
        assertEquals("compareTo failed",new Bid(BidType.CONTRA).compareTo(new Bid(BidType.CONTRA)),0);
        assertEquals("compareTo failed",new Bid(BidType.CARD,5,Color.CLUBS).compareTo(null),-1);
        assertEquals("compareTo failed",new Bid(BidType.CARD,5,Color.CLUBS).compareTo(new Bid(BidType.CARD,6,Color.CLUBS)),-1);
        assertEquals("compareTo failed",new Bid(BidType.CARD,5,Color.HEARTS).compareTo(new Bid(BidType.CARD,5,Color.CLUBS)),2);
        assertEquals("compareTo failed",new Bid(BidType.CARD,5,Color.CLUBS).compareTo(new Bid(BidType.PASS)),-1);
    }

    @Test
    public void testEquals() throws Exception {
        assertEquals("equals failed",new Bid(BidType.CARD,5,Color.CLUBS).equals(new Bid(BidType.CARD,5,Color.CLUBS)),true);
        assertEquals("equals failed",new Bid(BidType.CARD,5,Color.CLUBS).equals(new Bid(BidType.CARD,5,Color.HEARTS)),false);
        assertEquals("equals failed",new Bid(BidType.CARD,5,Color.CLUBS).equals(new Bid(BidType.CARD,7,Color.CLUBS)),false);
        assertEquals("equals failed",new Bid(BidType.CARD,5,Color.CLUBS).equals(new Bid(BidType.CONTRA)),false);
        assertEquals("equals failed",new Bid(BidType.CARD,5,Color.CLUBS).equals(new Bid(BidType.RECONTRA)),false);
        assertEquals("equals failed",new Bid(BidType.CARD,5,Color.CLUBS).equals(new Bid(BidType.PASS)),false);
        assertEquals("equals failed",new Bid(BidType.CONTRA).equals(new Bid(BidType.CONTRA)),true);
        assertEquals("equals failed",new Bid(BidType.CONTRA).equals(new Bid(BidType.RECONTRA)),false);
        assertEquals("equals failed",new Bid(BidType.CONTRA).equals(new Bid(BidType.PASS)),false);
        assertEquals("equals failed",new Bid(BidType.RECONTRA).equals(new Bid(BidType.RECONTRA)),true);
        assertEquals("equals failed",new Bid(BidType.RECONTRA).equals(new Bid(BidType.PASS)),false);
        assertEquals("equals failed",new Bid(BidType.PASS).equals(new Bid(BidType.PASS)),true);
        assertEquals("equals failed",new Bid(BidType.CONTRA).equals(null),false);
    }
}