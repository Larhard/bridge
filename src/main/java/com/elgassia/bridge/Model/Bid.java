package com.elgassia.bridge.Model;

/**
 * Created by vereena on 6/20/15.
 */
public class Bid {
    private BidType type;
    private Integer count;
    private Color color;

    public BidType getType() {
        return type;
    }

    public Integer getCount() {
        return count;
    }

    public Color getColor() {
        return color;
    }

    Bid(BidType type) {
        this.type = type;
        if (this.type == BidType.CARD) {
            throw new IllegalArgumentException();
        }
    }

    Bid(BidType type, Integer count, Color color) {
        this.type = type;
        if (this.type == BidType.CARD) {
            this.count = count;
            this.color = color;
        }
    }

}
