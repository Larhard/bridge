package com.elgassia.bridge.view.tui.commands.team.lobby;

import com.elgassia.bridge.Model.Card;
import com.elgassia.bridge.Model.ChooseStrategy;
import com.elgassia.bridge.Model.RandomStrategy;
import com.elgassia.bridge.adapter.LobbyAdapter;
import com.elgassia.bridge.utils.Cards;
import com.elgassia.bridge.view.tui.Command;

import java.util.ArrayList;
import java.util.List;

public class SetDeck extends Command {
    private final LobbyAdapter currentLobbyAdapter;

    public SetDeck(LobbyAdapter currentLobbyAdapter) {
        this.currentLobbyAdapter = currentLobbyAdapter;
    }

    private void usage() {
        System.out.println("usage: random | <deck>");
        System.out.println("  deck: 13*4*card");
        System.out.println("  card: <rank> <suit>");
    }

    @Override
    public void execute() {
        if (args.length == 2) {
            if ("random".equals(args[1])) {
                currentLobbyAdapter.setDeckStrategy(new RandomStrategy());
                return;
            }
        }
        System.out.println(args.length);
        System.out.println(13*4*2+1);
        if (args.length == 13*4*2 + 1) {
            Card[] cards = new Card[13*4];
            for (int i = 1; i < 13*4*2 + 1; i += 2) {
                cards[i/2] = Cards.fromString(args[i], args[i + 1]);
                if (cards[i/2] == null) {
                    System.out.println("invalid card: " + args[i] + " " + args[i+1]);
                    return;
                }
            }

            @SuppressWarnings("unchecked") List<Card>[] decks = new List[4];
            for (int i = 0; i < 13*4; ++i) {
                if (decks[i/13] == null) {
                    decks[i/13] = new ArrayList<>();
                }
                decks[i/13].add(cards[i]);
            }
            currentLobbyAdapter.setDeckStrategy(new ChooseStrategy(decks));
            return;
        }
        usage();
    }

    @Override
    public String description() {
        return "Set deck strategy";
    }
}
