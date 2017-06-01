package top.yunp.androidgame2d.events;

import android.view.MotionEvent;

import top.yunp.lib.java.event.Event;

public class G2DMotionEvent extends Event {

    public static final String TOUCH_DOWN = "touchDown";
    public static final String TOUCH_MOVE = "touchMove";
    public static final String TOUCH_UP = "touchUp";


    public G2DMotionEvent(String type, MotionEvent relatedMotionEvent) {
        super(type);
        this.relatedMotionEvent = relatedMotionEvent;
    }

    @Override
    public G2DMotionEvent clone() {
        return new G2DMotionEvent(getName(), getRelatedMotionEvent());
    }

    public MotionEvent getRelatedMotionEvent() {
        return relatedMotionEvent;
    }

    private MotionEvent relatedMotionEvent = null;
}
