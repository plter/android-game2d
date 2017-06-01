

package top.yunp.androidgame2d.events;

import android.view.MotionEvent;

import top.yunp.androidgame2d.display.Display;

import top.yunp.lib.java.event.Event;

/**
 * @author xtiqin, plter.com
 */
public class TouchEvent extends Event {


    public static final String TOUCH_DOWN = "touchDown";
    public static final String TOUCH_MOVE = "touchMove";
    public static final String TOUCH_UP = "touchUp";


    public TouchEvent(String type, Display target, MotionEvent relatedMotionEvent) {
        super(type);
        this.relatedMotionEvent = relatedMotionEvent;
        this.target = target;
    }

    public float getX() {
        return getGlobalX() - target.getGlobalX();
    }

    public float getY() {
        return getGlobalY() - target.getGlobalY();
    }

    public float getX(int index) {
        return getGlobalX(index) - target.getGlobalX();
    }

    public float getY(int index) {
        return getGlobalY(index) - target.getGlobalY();
    }

    public float getGlobalX() {
        return relatedMotionEvent.getX();
    }

    public float getGlobalY() {
        return relatedMotionEvent.getY();
    }

    public float getGlobalX(int index) {
        return relatedMotionEvent.getX(index);
    }

    public float getGlobalY(int index) {
        return relatedMotionEvent.getY(index);
    }

    public float getPointerCount() {
        return relatedMotionEvent.getPointerCount();
    }

    public MotionEvent getRelatedMotionEvent() {
        return relatedMotionEvent;
    }

    @Override
    public TouchEvent clone() {
        return new TouchEvent(getName(), target, relatedMotionEvent);
    }

    private MotionEvent relatedMotionEvent = null;
    private Display target = null;
}
