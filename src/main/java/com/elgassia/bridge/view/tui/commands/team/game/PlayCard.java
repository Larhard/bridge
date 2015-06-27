package com.elgassia.bridge.view.tui.commands.team.game;

import com.elgassia.bridge.Model.Card;
import com.elgassia.bridge.adapter.GameAdapter;
import com.elgassia.bridge.exception.BridgeLogicException;
import com.elgassia.bridge.utils.Cards;
import com.elgassia.bridge.view.tui.Command;

public class PlayCard extends Command {
    private final GameAdapter gameAdapter;

    public PlayCard(GameAdapter gameAdapter) {
        this.gameAdapter = gameAdapter;
    }

    @Override
    public void execute() {
        if (args.length != 3) {
            usage();
            return;
        }

        Card card = Cards.fromString(args[1], args[2]);
        if (card == null) {
            System.out.println("invalid card rank or color");
            return;
        }

        try {
            gameAdapter.playCard(card);
        } catch (BridgeLogicException e) {
            System.out.println(e.getMessage());
        }
    }

    private void usage() {
        System.out.println("usage: <rank> <color>");
    }
}
