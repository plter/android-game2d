package top.yunp.e008donttouchwhiterect;

import java.util.ArrayList;
import java.util.List;

import top.yunp.androidgame2d.display.Container;
import top.yunp.androidgame2d.display.Display;
import top.yunp.androidgame2d.events.TouchEvent;
import top.yunp.lib.java.event.EventListener;

/**
 * Created by plter on 6/5/17.
 */

public class RectLine extends Container {

    private final top.yunp.lib.java.event.EventListener<top.yunp.androidgame2d.events.TouchEvent, top.yunp.androidgame2d.display.Display> rectTouchDownHandler = new EventListener<TouchEvent, Display>() {
        @Override
        public boolean onReceive(TouchEvent event, Display display) {
            if (getOnRectSelected() != null) {
                getOnRectSelected().onSelect((Rect) display, RectLine.this);
            }

            return false;
        }
    };
    private List<Rect> rects = new ArrayList<>();

    public RectLine() {

        Rect r;

        for (int i = 0; i < 4; i++) {
            r = new Rect(false);
            r.setBorderWidth(5);
            r.setX(Config.getCardWidth() * i);
            add(r);
            rects.add(r);
            r.touchDown.add(rectTouchDownHandler);
        }

        rects.get((int) (Math.random() * rects.size())).setBlack(true);
    }

    private OnRectSelected onRectSelected = null;

    public void setOnRectSelected(OnRectSelected onRectSelected) {
        this.onRectSelected = onRectSelected;
    }

    public OnRectSelected getOnRectSelected() {
        return onRectSelected;
    }

    public interface OnRectSelected {

        void onSelect(Rect rect, RectLine target);
    }
}
