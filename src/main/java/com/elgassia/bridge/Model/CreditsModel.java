package com.elgassia.bridge.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vereena on 6/19/15.
 */
public class CreditsModel {
    public List<String> getCredits()
    {
        ArrayList<String>creditsList=new ArrayList<>();
        creditsList.add("Bartłomiej Puget");
        creditsList.add("Dominika Salawa");
        return creditsList;
    }
}
