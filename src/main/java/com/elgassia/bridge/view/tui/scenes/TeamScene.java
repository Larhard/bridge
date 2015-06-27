package com.elgassia.bridge.view.tui.scenes;

import com.elgassia.bridge.view.tui.Commands;
import com.elgassia.bridge.view.tui.Scene;
import com.elgassia.bridge.view.tui.View;
import com.elgassia.bridge.view.tui.commands.team.NextPlayer;
import com.elgassia.bridge.view.tui.commands.team.bidding.Bid;
import com.elgassia.bridge.view.tui.commands.team.game.PlayCard;
import com.elgassia.bridge.view.tui.commands.team.lobby.*;

import java.util.Observable;
import java.util.Observer;

public class TeamScene extends Scene implements Observer {
    private Commands commands;
    private View view;

    @Override
    public void init(View view) {
        super.init(view);
        view.getAdapter().getTeamAdapter().addObserver(this);
    }

    @Override
    protected void prepareCommands(Commands commands, View view) {
        this.commands = commands;
        this.view = view;

        commands.clear();

        super.prepareCommands(commands, view);
        switch (view.getAdapter().getTeamAdapter().getState()) {
            case LOBBY:
                commands.add("next", new NextPlayer(view.getAdapter().getTeamAdapter()));
                commands.add("set_name", new SetName(view.getAdapter().getTeamAdapter().getLobbyAdapter()));
                commands.add("start", new StartGame(view.getAdapter().getTeamAdapter().getLobbyAdapter()));
                commands.add("random_teams", new SetRandomTeams(view.getAdapter().getTeamAdapter().getLobbyAdapter()));
                commands.add("set_team", new SetTeam(view.getAdapter().getTeamAdapter().getLobbyAdapter()));
                commands.add("status", new LobbyStatus(view.getAdapter().getTeamAdapter()));
                System.out.println("Player: " + view.getAdapter().getTeamAdapter().getName());
                break;
            case BIDDING:
                commands.add("next", new NextPlayer(view.getAdapter().getTeamAdapter()));
                commands.add("bid", new Bid(view.getAdapter().getTeamAdapter().getBiddingAdapter()));
                break;
            case GAME:
                commands.add("next", new NextPlayer(view.getAdapter().getTeamAdapter()));
                commands.add("play", new PlayCard(view.getAdapter().getTeamAdapter().getGameAdapter()));
                break;
            default:
                assert false;
        }
    }

    @Override
    public void update(Observable observable, Object o) {
        prepareCommands(commands, view);
    }
}
