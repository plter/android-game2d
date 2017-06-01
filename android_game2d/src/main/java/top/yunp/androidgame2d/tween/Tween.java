package top.yunp.androidgame2d.tween;

import top.yunp.androidgame2d.display.Display;
import top.yunp.androidgame2d.display.GameView;
import top.yunp.androidgame2d.events.GameViewEvent;
import top.yunp.androidgame2d.events.TweenEvent;

import top.yunp.lib.java.event.EventListener;
import top.yunp.lib.java.event.EventListenerList;

public abstract class Tween {

    public Tween() {
    }

    public Tween(Display target) {
        setTarget(target);
    }

    private Display target = null;

    public Display getTarget() {
        return target;
    }

    public Tween setTarget(Display target) {
        this.target = target;
        return this;
    }

    public Tween start() {
        if (running || target == null) {
            return this;
        }
        GameView gv = target.getGameView();
        if (gv == null) {
            return this;
        }

        onStart();
        running = true;
        tweenStart.dispatch(new TweenEvent(TweenEvent.TWEEN_START), this);
        currentFrame = 0;
        gv.enterFrame.add(enterFrameHandler);
        return this;
    }

    protected abstract void onEnterFrame(int currentFrame, int frames);

    protected void onStart() {
    }

    protected void onEnd() {
    }

    public int getFrames() {
        return frames;
    }

    public void setFrames(int frames) {
        this.frames = frames;
    }

    private int frames = 20, currentFrame = 0;

    private final EventListener<GameViewEvent> enterFrameHandler = new EventListener<GameViewEvent>(null) {

        @Override
        public boolean onReceive(GameViewEvent event, Object target) {
            currentFrame++;
            onEnterFrame(currentFrame, frames);
            if (currentFrame >= frames) {
                getTarget().getGameView().enterFrame.remove(enterFrameHandler);

                running = false;
                onEnd();
                tweenEnd.dispatch(new TweenEvent(TweenEvent.TWEEN_END), this);
            }
            return false;
        }
    };

    public boolean isRunning() {
        return running;
    }

    private boolean running = false;

    public final EventListenerList<TweenEvent> tweenStart = new EventListenerList<TweenEvent>();
    public final EventListenerList<TweenEvent> tweenEnd = new EventListenerList<TweenEvent>();
}
