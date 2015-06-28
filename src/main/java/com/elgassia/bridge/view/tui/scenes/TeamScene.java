package com.elgassia.bridge.view.tui.scenes;

import com.elgassia.bridge.adapter.BiddingAdapter;
import com.elgassia.bridge.adapter.GameAdapter;
import com.elgassia.bridge.adapter.LobbyAdapter;
import com.elgassia.bridge.adapter.UserTeamAdapter;
import com.elgassia.bridge.bot.Bot;
import com.elgassia.bridge.view.tui.Commands;
import com.elgassia.bridge.view.tui.Scene;
import com.elgassia.bridge.view.tui.View;
import com.elgassia.bridge.view.tui.commands.team.NextPlayer;
import com.elgassia.bridge.view.tui.commands.team.bidding.Bid;
import com.elgassia.bridge.view.tui.commands.team.bidding.BiddingStatus;
import com.elgassia.bridge.view.tui.commands.team.game.GameStatus;
import com.elgassia.bridge.view.tui.commands.team.game.PlayCard;
import com.elgassia.bridge.view.tui.commands.team.lobby.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class TeamScene extends Scene implements Observer {
    private Commands commands;
    private View view;
    private List<UserTeamAdapter> userTeamAdapters;
    private int currentPlayer;

    @Override
    public void init(View view) {
        com.elgassia.bridge.adapter.TeamAdapter teamAdapter = view.getAdapter().getTeamAdapter();

        userTeamAdapters = new ArrayList<>();
        for (int userId : teamAdapter.getPlayersOrder()) {
            userTeamAdapters.add(teamAdapter.getPlayerTeamAdapter(userId));
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
                System.out.println("Player: " + getCurrentUserTeamAdapter().getName());
                break;
            case BIDDING:
                BiddingAdapter currentBiddingAdapter = getCurrentUserTeamAdapter().getBiddingAdapter();

                commands.add("next", new NextPlayer(this, view.getAdapter().getTeamAdapter()));
                commands.add("bid", new Bid(currentBiddingAdapter));
                commands.add("status", new BiddingStatus(getCurrentUserTeamAdapter()));
                break;
            case GAME:
                GameAdapter currentGameAdapter = getCurrentUserTeamAdapter().getGameAdapter();

                commands.add("next", new NextPlayer(this, view.getAdapter().getTeamAdapter()));
                commands.add("play", new PlayCard(currentGameAdapter));
                commands.add("status", new GameStatus(getCurrentUserTeamAdapter()));
                break;
            default:
                assert false;
        }
    }

    @Override
    public void update(Observable observable, Object o) {
        prepareCommands(commands, view);
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
