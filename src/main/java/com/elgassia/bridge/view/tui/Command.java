package com.elgassia.bridge.view.tui;

abstract public class Command {
    protected String[] args;

    void setArgs(String[] args) {
        this.args = args;
    }

    public abstract void execute();
}
