package top.yunp.androidgame2d.display;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;

import top.yunp.androidgame2d.events.G2DMotionEvent;
import top.yunp.androidgame2d.events.GameViewEvent;
import top.yunp.androidgame2d.events.TouchEvent;

import java.util.Timer;
import java.util.TimerTask;

import top.yunp.lib.java.event.EventListenerList;

public class GameView extends SurfaceView implements IContainer {


    public GameView(Context context) {
        super(context);
        initProperties();
    }


    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initProperties();
    }


    public GameView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initProperties();
    }


    private void initProperties() {
        getHolder().addCallback(callback);
        setOnTouchListener(touchListener);
        rootContainer.internal_setGameView(this);
    }


    private void redraw() {
        Canvas canvas = getHolder().lockCanvas();
        if (canvas != null) {
            canvas.drawColor(getGameViewBackground());
            rootContainer.draw(canvas);
            getHolder().unlockCanvasAndPost(canvas);
        }

        enterFrame.dispatch(new GameViewEvent(GameViewEvent.ENTER_FRAME), GameView.this);
    }

    private void startTimer() {

        if (timerTask == null) {
            timerTask = new TimerTask() {

                @Override
                public void run() {
                    redraw();
                }
            };

            timer.schedule(timerTask, 1000 / getFps(), 1000 / getFps());
        }
    }

    private void stopTimer() {
        if (timerTask != null) {
            timerTask.cancel();
            timerTask = null;
        }
    }

    public int getFps() {
        return fps;
    }


    /**
     * 帧频最小值为1，如果传入参数小于1，则系统自动使用1作为帧频
     *
     * @param fps
     */
    public void setFps(int fps) {
        if (fps < 1) {
            fps = 1;
        }

        this.fps = fps;

        if (running) {
            stopTimer();
            startTimer();
        }
    }

    public int getGameViewBackground() {
        return gameViewBackground;
    }


    public void setGameViewBackground(int gameViewBackground) {
        this.gameViewBackground = gameViewBackground;
    }

    public Stage getRoot() {
        return rootContainer;
    }

    private final Timer timer = new Timer();
    private TimerTask timerTask = null;
    private int fps = 20;
    private boolean running = false;
    private int gameViewBackground = Color.WHITE;
    private final Stage rootContainer = new Stage();

    public final EventListenerList<GameViewEvent, GameView> enterFrame = new EventListenerList<>();
    public final EventListenerList<GameViewEvent, GameView> surfaceDestroyed = new EventListenerList<>();
    public final EventListenerList<GameViewEvent, GameView> surfaceCreated = new EventListenerList<>();
    public final EventListenerList<GameViewEvent, GameView> surfaceChanged = new EventListenerList<>();
    public final EventListenerList<G2DMotionEvent, GameView> touch = new EventListenerList<>();
    public final EventListenerList<G2DMotionEvent, GameView> touchMove = new EventListenerList<>();
    public final EventListenerList<G2DMotionEvent, GameView> touchDown = new EventListenerList<>();
    public final EventListenerList<G2DMotionEvent, GameView> touchUp = new EventListenerList<>();

    private final Callback callback = new Callback() {

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            stopTimer();
            surfaceDestroyed.dispatch(new GameViewEvent(GameViewEvent.SURFACE_DESTROYED), GameView.this);
            running = false;
        }

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            running = true;
            surfaceCreated.dispatch(new GameViewEvent(GameViewEvent.SURFACE_CREATED), GameView.this);

            redraw();
            startTimer();
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width,
                                   int height) {
            surfaceChanged.dispatch(new GameViewEvent(GameViewEvent.SURFACE_CHANGED), GameView.this);
        }
    };


    private final OnTouchListener touchListener = new OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_MOVE:
                    rootContainer.internal_dispatchTouchEvent(new TouchEvent(TouchEvent.TOUCH_MOVE, rootContainer, event));
                    touch.dispatch(new G2DMotionEvent(G2DMotionEvent.TOUCH_MOVE, event), GameView.this);
                    touchMove.dispatch(new G2DMotionEvent(G2DMotionEvent.TOUCH_MOVE, event), GameView.this);
                    break;
                case MotionEvent.ACTION_DOWN:
                    rootContainer.internal_dispatchTouchEvent(new TouchEvent(TouchEvent.TOUCH_DOWN, rootContainer, event));
                    touch.dispatch(new G2DMotionEvent(G2DMotionEvent.TOUCH_DOWN, event), GameView.this);
                    touchDown.dispatch(new G2DMotionEvent(G2DMotionEvent.TOUCH_DOWN, event), GameView.this);
                    break;
                case MotionEvent.ACTION_UP:
                    rootContainer.internal_dispatchTouchEvent(new TouchEvent(TouchEvent.TOUCH_UP, rootContainer, event));
                    touch.dispatch(new G2DMotionEvent(G2DMotionEvent.TOUCH_UP, event), GameView.this);
                    touchUp.dispatch(new G2DMotionEvent(G2DMotionEvent.TOUCH_UP, event), GameView.this);
                    break;
            }

            return true;
        }
    };

    @Override
    public Display add(Display display) {
        return rootContainer.add(display);
    }


    @Override
    public Display addAt(Display display, int index) {
        return rootContainer.addAt(display, index);
    }


    @Override
    public boolean remove(Display display) {
        return rootContainer.remove(display);
    }


    @Override
    public Display remove(int index) {
        return rootContainer.remove(index);
    }


    @Override
    public Display get(int index) {
        return rootContainer.get(index);
    }


    @Override
    public void removeAll() {
        rootContainer.removeAll();
    }


    @Override
    public void swap(Display display, Display display2) {
        rootContainer.swap(display, display2);
    }


    @Override
    public void swap(int index, int index2) {
        rootContainer.swap(index, index2);
    }


    @Override
    public int getIndex(Display display) {
        return rootContainer.getIndex(display);
    }

    @Override
    public boolean contains(Display display) {
        return rootContainer.contains(display);
    }
}
