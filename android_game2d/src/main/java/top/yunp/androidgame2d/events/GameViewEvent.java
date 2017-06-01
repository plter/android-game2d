package top.yunp.androidgame2d.events;

import top.yunp.lib.java.event.Event;

public class GameViewEvent extends Event {


    public static final String SURFACE_CREATED = "surfaceCreated";
    public static final String SURFACE_DESTROYED = "surfaceDestroyed";
    public static final String SURFACE_CHANGED = "surfaceChanged";
    public static final String ENTER_FRAME = "enterFrame";


    public GameViewEvent(String type) {
        super(type);
    }

    @Override
    public GameViewEvent clone() {
        return new GameViewEvent(getName());
    }
}
