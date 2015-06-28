package com.elgassia.bridge.bot.crazy_monkey;

import com.elgassia.bridge.Model.Bid;
import com.elgassia.bridge.Model.BidType;
import com.elgassia.bridge.Model.Card;
import com.elgassia.bridge.Model.Color;
import com.elgassia.bridge.adapter.BiddingAdapter;
import com.elgassia.bridge.adapter.GameAdapter;
import com.elgassia.bridge.adapter.LobbyAdapter;
import com.elgassia.bridge.adapter.UserTeamAdapter;
import com.elgassia.bridge.adapter.main.TeamAdapter;
import com.elgassia.bridge.bot.Bot;
import com.elgassia.bridge.exception.BridgeLogicException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Thread.sleep;

public class CrazyMonkey extends Bot {
    private final UserTeamAdapter teamAdapter;
    final Random random = new Random();

    public CrazyMonkey(UserTeamAdapter teamAdapter) {
        this.teamAdapter = teamAdapter;
    }

    @Override
    public void run() {
        //noinspection InfiniteLoopStatement
        while (true) {
            final TeamAdapter.State state = teamAdapter.getState();
            switch (state) {
                case LOBBY:
                    crazyLobby();
                    break;
                case BIDDING:
                    crazyBidding();
                    break;
                case GAME:
                    crazyGame();
                    break;
            }

            try {
                sleep(100);
            } catch (InterruptedException ignored) {
            }
        }
    }

    private void crazyLobby() {
        final LobbyAdapter lobbyAdapter = teamAdapter.getLobbyAdapter();

        try {
            lobbyAdapter.setRandomTeam();
        } catch (BridgeLogicException ignored) {
        }

        try {
            lobbyAdapter.startGame();
        } catch (BridgeLogicException ignored) {
        }
    }

    private void crazyBidding() {
        final BiddingAdapter biddingAdapter = teamAdapter.getBiddingAdapter();

        BidType bidType = BidType.values()[random.nextInt(BidType.values().length)];
        if (bidType == BidType.CARD) {
            int count = random.nextInt(7) + 1;
            Color color = Color.values()[random.nextInt(Color.values().length)];
            try {
                biddingAdapter.bid(new Bid(bidType, count, color));
            } catch (BridgeLogicException ignored) {
            }
        } else {
            try {
                biddingAdapter.bid(new Bid(bidType));
            } catch (BridgeLogicException ignored) {
            }
        }
    }

    private void crazyGame() {
        final GameAdapter gameAdapter = teamAdapter.getGameAdapter();

        List<Card> cards = new ArrayList<>(gameAdapter.getCards());
        cards.addAll(gameAdapter.getGrandpasCards());

        if (cards.size() > 0) {
            Card card = cards.get(random.nextInt(cards.size()));

            try {
                gameAdapter.playCard(card);
            } catch (BridgeLogicException ignored) {
            }
        }
    }
}
