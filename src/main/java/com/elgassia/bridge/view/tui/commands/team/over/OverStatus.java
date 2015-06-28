package com.elgassia.bridge.view.tui.commands.team.over;

import com.elgassia.bridge.Model.Card;
import com.elgassia.bridge.adapter.GameOverAdapter;
import com.elgassia.bridge.adapter.TeamAdapter;
import com.elgassia.bridge.view.tui.Command;

import java.util.Arrays;

public class OverStatus extends Command {
    private final GameOverAdapter gameOverAdapter;
    private final TeamAdapter teamAdapter;

    public OverStatus(GameOverAdapter gameOverAdapter, TeamAdapter teamAdapter) {
        this.gameOverAdapter = gameOverAdapter;
        this.teamAdapter = teamAdapter;
    }

    @Override
    public void execute() {
        System.out.println("  === Game Over ===");

        System.out.println();
        System.out.println("Players:");
        for (int player : teamAdapter.getPlayersOrder()) {
            System.out.println("  " + teamAdapter.getPlayerName(player) + " " + teamAdapter.getPlayerTeam(player));
        }

        System.out.println();
        System.out.println("Winner Team: " + gameOverAdapter.getGameWinner());
        System.out.println("Turns won by the playing team: " + gameOverAdapter.getTurnsWonByThePlayingTeam());
        System.out.println("Contract: " + teamAdapter.getContract() + " " + teamAdapter.getAtu());

        System.out.println();
        System.out.println("Turns:");
        final int[] whoStartedTurn = gameOverAdapter.getWhoStartedTurn();
        final Card[][] playedCards = gameOverAdapter.getPlayedCards();
        for (int i = 0; i < whoStartedTurn.length; ++i) {
            System.out.println(teamAdapter.getPlayerName(whoStartedTurn[i]) + ": " + Arrays.toString(playedCards[i]));
        }
    }
}
