package top.yunp.androidgame2d.events;

import top.yunp.lib.java.event.Event;

public class TweenEvent extends Event {


    public static final String TWEEN_START = "tweenStart";
    public static final String TWEEN_END = "tweenEnd";

    public TweenEvent(String type) {
        super(type);
    }

    @Override
    public TweenEvent clone() {
        return new TweenEvent(getName());
    }
}
