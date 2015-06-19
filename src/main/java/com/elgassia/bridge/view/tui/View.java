package com.elgassia.bridge.view.tui;

import com.elgassia.bridge.adapter.Adapter;

import java.util.Observable;

public class View implements com.elgassia.bridge.view.View {
    @Override
    public void init(Adapter adapter) {
        adapter.addObserver(this);
    }

    @Override
    public void update(Observable observable, Object o) {

    }
}
