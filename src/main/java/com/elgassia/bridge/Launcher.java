package com.elgassia.bridge;

import com.elgassia.bridge.Model.MainModel;
import com.elgassia.bridge.adapter.Adapter;
import com.elgassia.bridge.view.View;

import java.util.Arrays;
import java.util.List;

public class Launcher {
    public static void main(String[] args) {
        MainModel main_model = new MainModel();

        Adapter main_adapter = new com.elgassia.bridge.adapter.main.Adapter();
        main_adapter.init(main_model);

        List<View> views = Arrays.asList(new View[] {
                new com.elgassia.bridge.view.tui.View(),
        });

        for (View view : views) {
            view.init(main_adapter);
        }
    }
}
