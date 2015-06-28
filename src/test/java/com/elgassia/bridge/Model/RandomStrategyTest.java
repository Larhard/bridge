package com.elgassia.bridge.Model;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class RandomStrategyTest {

    @Test
    public void testGetCards() throws Exception {
        List<Card> [] lists=new RandomStrategy().getCards();
        assertEquals("getCards failed",lists.length,4);
        assertEquals("getCards failed",lists[0].size(),13);
        assertEquals("getCards failed",lists[1].size(),13);
        assertEquals("getCards failed",lists[2].size(),13);
        assertEquals("getCards failed",lists[3].size(),13);
    }
}