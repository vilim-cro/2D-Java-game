package com.vilim;

import java.awt.*;

public abstract class State {
    //Current state of the game
    private static State currentState = null;

    public static State getState() {
        return currentState;
    }

    public static void setState (State state) {
        currentState = state;
    }

    //Class things
    protected Handler handler;

    public State(Handler handler) {
        this.handler = handler;
    }

    public abstract void tick();

    public abstract void render (Graphics g);


}
