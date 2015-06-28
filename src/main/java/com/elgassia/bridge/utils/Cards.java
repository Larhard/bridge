package com.elgassia.bridge.utils;

import com.elgassia.bridge.Model.Card;

public class Cards {
    public static Card fromString(String rank_str, String color_str) {
        Card.Rank rank = null;
        Card.Suit color = null;

        switch (rank_str.toLowerCase()) {
            case "2":
                rank = Card.Rank.TWO;
                break;
            case "3":
                rank = Card.Rank.THREE;
                break;
            case "4":
                rank = Card.Rank.FOUR;
                break;
            case "5":
                rank = Card.Rank.FIVE;
                break;
            case "6":
                rank = Card.Rank.SIX;
                break;
            case "7":
                rank = Card.Rank.SEVEN;
                break;
            case "8":
                rank = Card.Rank.EIGHT;
                break;
            case "9":
                rank = Card.Rank.NINE;
                break;
            case "10":
                rank = Card.Rank.TEN;
                break;
            case "j":
            case "jack":
                rank = Card.Rank.JACK;
                break;
            case "q":
            case "queen":
                rank = Card.Rank.QUEEN;
                break;
            case "k":
            case "king":
                rank = Card.Rank.KING;
                break;
            case "a":
            case "ace":
                rank = Card.Rank.ACE;
                break;
        }

        switch (color_str.toLowerCase()) {
            case "c":
            case "clubs":
                color = Card.Suit.CLUBS;
                break;
            case "h":
            case "hearts":
                color = Card.Suit.HEARTS;
                break;
            case "d":
            case "diamonds":
                color = Card.Suit.DIAMONDS;
                break;
            case "s":
            case "spades":
                color = Card.Suit.SPADES;
                break;
        }

        if (color == null || rank == null) {
            return null;
        }

        return new Card(rank, color);
    }
}
