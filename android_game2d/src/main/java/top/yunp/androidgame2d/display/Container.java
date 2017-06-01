package top.yunp.androidgame2d.display;

import android.graphics.Canvas;
import android.graphics.RectF;

import top.yunp.androidgame2d.events.TouchEvent;
import top.yunp.lib.java.lang.Array;
import top.yunp.lib.java.lang.ArrayItem;
import top.yunp.lib.java.lang.ArrayLoopCallback;

public class Container extends Display implements IContainer {

    @Override
    public Display add(Display display) {

        if (contains(display)) {
            return display;
        }

        displays.push(display);
        display.internal_setParent(this);
        return display;
    }

    @Override
    public Display addAt(Display display, int index) {

        if (contains(display)) {
            return display;
        }

        displays.insert(display, index);
        display.internal_setParent(this);
        return display;
    }

    @Override
    public boolean remove(Display display) {
        display.internal_setParent(null);
        return displays.remove(display) != null;
    }

    @Override
    public Display remove(int index) {
        get(index).internal_setParent(null);
        return displays.remove(index);
    }

    @Override
    public Display get(int index) {
        return displays.get(index);
    }

    @Override
    public void removeAll() {
        while (displays.length() > 0) {
            displays.remove(0).internal_setParent(null);
        }
    }

    @Override
    public void swap(Display display, Display display2) {
        swap(getIndex(display), getIndex(display2));
    }

    @Override
    public void swap(int index, int index2) {
        if (index <= -1 || index2 <= -1) {
            return;
        }

        int min, max;

        if (index < index2) {
            min = index;
            max = index2;
        } else if (index > index2) {
            min = index2;
            max = index;
        } else {
            return;
        }

        displays.insert(displays.remove(max), min);
        displays.insert(displays.remove(index + 1), max);
    }

    @Override
    public boolean contains(Display display) {
        return displays.contains(display);
    }

    private final Array<Display> displays = new Array<Display>();

    @Override
    public int getIndex(Display display) {
        return displays.indexOf(display);
    }

    @Override
    public final void draw(final Canvas canvas) {
        displays.each(new ArrayLoopCallback<Display>() {

            @Override
            public void onRead(Display d, ArrayItem<Display> currentItem) {
                if (d.isVisible()) {
                    d.internal_draw(canvas);
                }
            }
        });
    }


    @Override
    void internal_dispatchTouchEvent(final TouchEvent e) {

        displays.reverseEach(new ArrayLoopCallback<Display>() {

            @Override
            public void onRead(Display d, ArrayItem<Display> currentItem) {
                if (d.isVisible() && d.isTouchEnable() && d.hitTest(e.getX(), e.getY())) {
                    d.internal_dispatchTouchEvent(new TouchEvent(e.getName(), d, e.getRelatedMotionEvent()));

                    break_();
                }
            }
        });

        super.internal_dispatchTouchEvent(e);
    }

    @Override
    public RectF getBounds() {
        bounds.setEmpty();
        displays.each(new ArrayLoopCallback<Display>() {

            @Override
            public void onRead(Display current, ArrayItem<Display> currentItem) {
                bounds.union(current.getBounds());

            }
        });
        getBoundsMatrix().mapRect(bounds);
        return bounds;
    }

    private final RectF bounds = new RectF();
}
