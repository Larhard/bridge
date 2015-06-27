package com.elgassia.bridge.Model;

import java.io.IOException;

/**
 * Created by vereena on 6/19/15.
 */
public class MainModel {
    public TeamModel newGame()
    {
        return new TeamModel();
    }
    public TeamModel newGame(TeamModel.Memento memento) throws IOException, ClassNotFoundException {
        return memento.restore();
    }
    public CreditsModel getCredits()
    {
        return new CreditsModel();
    }
}
