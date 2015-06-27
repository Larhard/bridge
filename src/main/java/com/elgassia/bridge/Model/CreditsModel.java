package com.elgassia.bridge.Model;

import java.util.ArrayList;
import java.util.List;

public class CreditsModel {

    public List<String> getCredits()
    {
        ArrayList<String>creditsList=new ArrayList<>();
        creditsList.add("Bartłomiej Puget");
        creditsList.add("Dominika Salawa");
        return creditsList;
    }
}
