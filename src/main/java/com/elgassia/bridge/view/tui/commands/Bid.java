package com.elgassia.bridge.view.tui.commands;

import com.elgassia.bridge.Model.BidType;
import com.elgassia.bridge.utils.Colors;
import com.elgassia.bridge.adapter.BiddingAdapter;
import com.elgassia.bridge.view.tui.Command;

public class Bid extends Command {
    private final BiddingAdapter biddingAdapter;

    public Bid(BiddingAdapter biddingAdapter) {
        this.biddingAdapter = biddingAdapter;
    }

    @Override
    public void execute() {
        if (args.length == 2) {
            switch (args[1]) {
                case "pass":
                    biddingAdapter.bid(new com.elgassia.bridge.Model.Bid(BidType.PASS));
                    return;
                case "contra":
                    biddingAdapter.bid(new com.elgassia.bridge.Model.Bid(BidType.CONTRA));
                    return;
                case "recontra":
                    biddingAdapter.bid(new com.elgassia.bridge.Model.Bid(BidType.RECONTRA));
                    return;
            }
        } else if (args.length == 3) {
            Integer count = Integer.parseInt(args[1]);
            String color = args[2];
            biddingAdapter.bid(new com.elgassia.bridge.Model.Bid(BidType.CARD, count, Colors.fromString(color)));
            return;
        }
        usage();
    }

    private void usage() {
        System.out.println("usage: contra | recontra | pass | <count> <color>");
    }

    @Override
    public String description() {
        return "make a bid:\n\tcontra\n\trecontra\n\tpass\n\t<count> <color>";
    }
}
