package com.elgassia.bridge;

import com.elgassia.bridge.adapter.Adapter;
import com.elgassia.bridge.view.View;

import java.util.Arrays;
import java.util.List;

public class Bridge {
    public static void main(String[] args) {
        Adapter main_adapter = new com.elgassia.bridge.adapter.main.Adapter();

        List<View> views = Arrays.asList(new View[] {
                new com.elgassia.bridge.view.tui.View(),
        });

        for (View view : views) {
            view.init(main_adapter);
        }
    }
}
