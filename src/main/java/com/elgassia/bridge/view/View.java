package com.elgassia.bridge.view;

import com.elgassia.bridge.adapter.Adapter;

import java.util.Observer;

public interface View extends Observer {
    void init(Adapter adapter);
}