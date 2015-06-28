package com.elgassia.bridge.Model;

/**
 * Created by vereena on 6/20/15.
 */
public class Bid implements Comparable<Bid>{
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

    public Bid(BidType type) {
        this.type = type;
        if (this.type == BidType.CARD) {
            throw new IllegalArgumentException();
        }
    }

    public Bid(BidType type, Integer count, Color color) {
        this.type = type;
        if (this.type == BidType.CARD) {
            this.count = count;
            this.color = color;
        }
    }

    @Override
    public int compareTo(Bid o) {
        if(o==null)
            return -1;
        if(this.type==BidType.CARD && o.getType()==BidType.CARD)
        {
            if(this.count!=o.getCount())
                return this.count.compareTo(o.getCount());
            else if(this.color!=o.getColor())
                return this.color.compareTo(o.getColor());
            return 0;
        }
        else if(this.getType().equals(o.getType()))
            return 0;
        return -1;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==null)
            return false;
        if(obj.getClass().equals(Bid.class))
        {
            Bid x=(Bid)obj;
            if(x.getCount()==null)
            {
                if(this.getCount()!=null)
                    return false;
            }
            else if(!x.getCount().equals(this.getCount()))
                return false;
            if(!x.getType().equals(this.getType()))
                return false;
            if(x.getColor()==null)
            {
                if(this.getColor()!=null)
                    return false;
            }
            else if(!x.getColor().equals(this.getColor()))
                return false;
            return true;
        }
        return false;
    }
}
