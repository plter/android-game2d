package top.yunp.e008donttouchwhiterect;

import java.util.ArrayList;
import java.util.List;

import top.yunp.androidgame2d.display.Container;
import top.yunp.androidgame2d.display.Display;
import top.yunp.androidgame2d.events.TouchEvent;
import top.yunp.androidgame2d.events.TweenEvent;
import top.yunp.androidgame2d.tween.TranslateTween;
import top.yunp.androidgame2d.tween.Tween;
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
    private top.yunp.lib.java.event.EventListener<top.yunp.androidgame2d.events.TweenEvent, top.yunp.androidgame2d.tween.Tween> tweenEndHandler = new EventListener<TweenEvent, Tween>() {
        @Override
        public boolean onReceive(TweenEvent event, Tween tween) {
            tweenRunning = false;

            if (getOnLineMoveDownTweenEnd() != null) {
                getOnLineMoveDownTweenEnd().onEnd(RectLine.this);
            }

            return false;
        }
    };

    private TranslateTween tt;
    private boolean tweenRunning = false;

    public RectLine() {

        Rect r;

        //在行中添加4个方块
        for (int i = 0; i < 4; i++) {
            r = new Rect(false);
            r.setPadding(5);
            r.setX(Config.getCardWidth() * i);
            add(r);
            rects.add(r);
            r.touchDown.add(rectTouchDownHandler);
        }

        //随机将其中一个方块设置为黑色
        rects.get((int) (Math.random() * rects.size())).setBlack(true);

        //Create a TranslateTween object which is using to move this line down
        tt = new TranslateTween(this, 0, 0, 0, 0);
        tt.tweenEnd.add(tweenEndHandler);
        tt.setFrames(5);
    }


    public boolean isTweenRunning() {
        return tweenRunning;
    }

    private int positionIndex = 0;

    public void setPositionIndex(int index) {
        if (!tweenRunning) {
            this.positionIndex = index;

            this.setTouchEnable(index == 3);
        }
    }

    public int getPositionIndex() {
        return positionIndex;
    }

    public void setPositionYByIndex() {
        setY(Config.getCardHeight() * getPositionIndex());
    }


    /**
     * 根据索引位置将该行移动到特定的坐标位置
     */
    public void moveToTargetPositionByIndex() {
        if (!tweenRunning) {
            tweenRunning = true;

            tt.setStartY(getY());
            tt.setEndY(getPositionIndex() * Config.getCardHeight());
            tt.start();
        }
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


    private OnLineMoveDownTweenEnd onLineMoveDownTweenEnd = null;

    public void setOnLineMoveDownTweenEnd(OnLineMoveDownTweenEnd onLineMoveDownTweenEnd) {
        this.onLineMoveDownTweenEnd = onLineMoveDownTweenEnd;
    }

    public OnLineMoveDownTweenEnd getOnLineMoveDownTweenEnd() {
        return onLineMoveDownTweenEnd;
    }

    public interface OnLineMoveDownTweenEnd {
        void onEnd(RectLine target);
    }
}
