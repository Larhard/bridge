package com.elgassia.bridge.view.tui.commands.team.bidding;

import com.elgassia.bridge.Model.Card;
import com.elgassia.bridge.adapter.UserTeamAdapter;
import com.elgassia.bridge.view.tui.Command;

import java.util.Collections;
import java.util.List;

public class BiddingStatus extends Command {

    private final UserTeamAdapter teamAdapter;

    public BiddingStatus(UserTeamAdapter teamAdapter) {
        this.teamAdapter = teamAdapter;
    }

    @Override
    public void execute() {
        System.out.println("  === Bidding ===");
        System.out.println();
        System.out.println("Players / teams:");
        for (int player : teamAdapter.getPlayersOrder()) {
            System.out.println("  " + teamAdapter.getPlayerName(player) + " / " + teamAdapter.getPlayerTeam(player));
        }
        System.out.println();
        System.out.println("Cards:");
        System.out.print(" ");
        List<Card> cards = teamAdapter.getBiddingAdapter().getCards();
        Collections.sort(cards);
        for (Card card : cards) {
            System.out.print(" " + card.toString());
        }
        System.out.println();

        System.out.println();
        System.out.println("Current player: " + teamAdapter.getBiddingAdapter().getCurrentPlayer());
    }
}
