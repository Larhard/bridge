package com.elgassia.bridge.utils;

import com.elgassia.bridge.Model.Color;

public class Colors {
    public static Color fromString(String string) {
        switch (string.toLowerCase()) {
            case "c":
            case "clubs":
                return Color.CLUBS;
            case "h":
            case "hearts":
                return Color.HEARTS;
            case "d":
            case "diamonds":
                return Color.DIAMONDS;
            case "s":
            case "spades":
                return Color.SPADES;
            case "nt":
            case "notrump":
                return Color.NT;
        }
        return null;
    }
}
