package com.elgassia.bridge.view.tui.scenes;

import com.elgassia.bridge.Model.Card;
import com.elgassia.bridge.adapter.BiddingAdapter;
import com.elgassia.bridge.adapter.GameAdapter;
import com.elgassia.bridge.adapter.LobbyAdapter;
import com.elgassia.bridge.adapter.UserTeamAdapter;
import com.elgassia.bridge.adapter.main.TeamAdapter;
import com.elgassia.bridge.bot.Bot;
import com.elgassia.bridge.exception.BridgeLogicException;
import com.elgassia.bridge.view.tui.Commands;
import com.elgassia.bridge.view.tui.Scene;
import com.elgassia.bridge.view.tui.View;
import com.elgassia.bridge.view.tui.commands.team.NextPlayer;
import com.elgassia.bridge.view.tui.commands.team.bidding.Bid;
import com.elgassia.bridge.view.tui.commands.team.bidding.BiddingStatus;
import com.elgassia.bridge.view.tui.commands.team.game.GameStatus;
import com.elgassia.bridge.view.tui.commands.team.game.PlayCard;
import com.elgassia.bridge.view.tui.commands.team.lobby.*;
import com.elgassia.bridge.view.tui.commands.team.over.OverStatus;

import java.util.*;

public class TeamScene extends Scene implements Observer {
    private Commands commands;
    private View view;
    private List<UserTeamAdapter> userTeamAdapters;
    private int currentPlayer;

    Card[] last_turn;
    List<com.elgassia.bridge.Model.Bid> lastBidding;

    @Override
    public void init(View view) {
        com.elgassia.bridge.adapter.TeamAdapter teamAdapter = view.getAdapter().getTeamAdapter();

        userTeamAdapters = new ArrayList<>();
        for (int userId : teamAdapter.getPlayersOrder()) {
            userTeamAdapters.add(teamAdapter.getPlayerTeamAdapter(userId));
            userTeamAdapters.get(userTeamAdapters.size()-1).addObserver(this);
        }

        super.init(view);

        view.getAdapter().getTeamAdapter().addObserver(this);
    }

    public void resetCommands() {
        prepareCommands(commands, view);
    }

    @Override
    protected void prepareCommands(Commands commands, View view) {
        this.commands = commands;
        this.view = view;

        commands.clear();

        super.prepareCommands(commands, view);
        switch (view.getAdapter().getTeamAdapter().getState()) {
            case LOBBY:
                if (getUserTeamAdapters().size() > 0) {
                    LobbyAdapter currentLobbyAdapter = getCurrentUserTeamAdapter().getLobbyAdapter();

                    List<LobbyAdapter> lobbyAdapters = new ArrayList<>();
                    for (UserTeamAdapter adapter : userTeamAdapters) {
                        lobbyAdapters.add(adapter.getLobbyAdapter());
                    }
                    commands.add("next", new NextPlayer(this, view.getAdapter().getTeamAdapter()));
                    commands.add("set_name", new SetName(currentLobbyAdapter));
                    commands.add("start", new StartGame(lobbyAdapters));
                    commands.add("random_teams", new SetRandomTeams(lobbyAdapters));
                    commands.add("set_team", new SetTeam(currentLobbyAdapter));
                    commands.add("status", new LobbyStatus(getCurrentUserTeamAdapter()));
                    commands.add("set_bot", new SetBot(this, getCurrentUserTeamAdapter(), getCurrentPlayer()));
                }

                break;
            case BIDDING:
                if (getUserTeamAdapters().size() > 0) {
                    BiddingAdapter currentBiddingAdapter = getCurrentUserTeamAdapter().getBiddingAdapter();

                    commands.add("next", new NextPlayer(this, view.getAdapter().getTeamAdapter()));
                    commands.add("bid", new Bid(currentBiddingAdapter));
                    commands.add("status", new BiddingStatus(getCurrentUserTeamAdapter()));
                }

                break;
            case GAME:
                if (getUserTeamAdapters().size() > 0) {
                    GameAdapter currentGameAdapter = getCurrentUserTeamAdapter().getGameAdapter();

                    commands.add("next", new NextPlayer(this, view.getAdapter().getTeamAdapter()));
                    commands.add("play", new PlayCard(currentGameAdapter));
                    commands.add("status", new GameStatus(getCurrentUserTeamAdapter()));
                }

                break;
            case OVER:
                commands.add("status", new OverStatus(view.getAdapter().getTeamAdapter().getGameOverAdapter(), view.getAdapter().getTeamAdapter()));
                break;
            default:
                assert false;
        }
    }

    @Override
    public void update(Observable observable, Object o) {
        prepareCommands(commands, view);
        if (observable instanceof UserTeamAdapter) {
            final UserTeamAdapter teamAdapter = (UserTeamAdapter) observable;

            if (teamAdapter.getState() == TeamAdapter.State.BIDDING) {
                final BiddingAdapter biddingAdapter = teamAdapter.getBiddingAdapter();
                List<com.elgassia.bridge.Model.Bid> actualBidding = biddingAdapter.getBiddingHistory();

                if (!actualBidding.equals(lastBidding)) {
                    lastBidding = new ArrayList<>(actualBidding);

                    StringBuilder str = new StringBuilder();
                    System.out.println("Bidding History: " + actualBidding);
                }
            }

            if (teamAdapter.getState() == TeamAdapter.State.GAME && lastBidding != null) {
                final BiddingAdapter biddingAdapter = teamAdapter.getBiddingAdapter();
                List<com.elgassia.bridge.Model.Bid> actualBidding = biddingAdapter.getBiddingHistory();

                lastBidding = null;
                System.out.println("Bidding History: " + actualBidding);
            }

            if (teamAdapter.getState() == TeamAdapter.State.GAME) {
                final GameAdapter gameAdapter = teamAdapter.getGameAdapter();

                try {
                    Card[] actual_turn = gameAdapter.turnHistory();

                    if (!Arrays.equals(actual_turn, last_turn)) {
                        last_turn = Arrays.copyOf(actual_turn, actual_turn.length);
                        try {
                            System.out.println("Previous turn by " + gameAdapter.whoStartedPreviousTurn() + ": " + Arrays.toString(gameAdapter.previousTurnHistory()));
                        } catch (BridgeLogicException ignored) {
                        }
                        System.out.println("Actual turn by " + gameAdapter.whoStartedTurn() + ": " + Arrays.toString(gameAdapter.turnHistory()));
                    }
                } catch (ArrayIndexOutOfBoundsException ignored) {
                }
            }

            if (teamAdapter.getState() == TeamAdapter.State.OVER && last_turn != null) {
                final GameAdapter gameAdapter = teamAdapter.getGameAdapter();

                last_turn = null;

                last_turn = null;
                try {
                    System.out.println("Last turn by " + gameAdapter.whoStartedPreviousTurn() + ": " + Arrays.toString(gameAdapter.previousTurnHistory()));
                } catch (BridgeLogicException e) {
                    throw new Error(e.getCause());
                }
            }
        }
    }

    public List<UserTeamAdapter> getUserTeamAdapters() {
        return userTeamAdapters;
    }

    public void setUserTeamAdapters(List<UserTeamAdapter> userTeamAdapters) {
        this.userTeamAdapters = userTeamAdapters;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer % getUserTeamAdapters().size();
    }

    public void nextPlayer() {
        if (getUserTeamAdapters().size() > 0) {
            setCurrentPlayer(getCurrentPlayer() + 1);
        }
        resetCommands();
    }

    public void setBot(int playerId, Bot bot) {
        userTeamAdapters.remove(playerId);
        nextPlayer();

        Thread bot_thread = new Thread(bot);
        bot_thread.setDaemon(true);
        bot_thread.start();
    }

    public UserTeamAdapter getCurrentUserTeamAdapter() {
        return getUserTeamAdapters().get(getCurrentPlayer());
    }
}
