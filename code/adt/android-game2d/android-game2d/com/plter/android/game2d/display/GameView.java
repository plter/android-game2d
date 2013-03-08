package com.plter.android.game2d.display;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;

import com.plter.android.game2d.events.G2DMotionEvent;
import com.plter.android.game2d.events.GameViewEvent;
import com.plter.android.game2d.events.TouchEvent;
import com.plter.lib.java.event.EventListenerList;
import com.plter.lib.java.lang.PObject;

public class GameView extends SurfaceView implements IContainer{


	public GameView(Context context){
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


	private void initProperties(){
		getHolder().addCallback(callback);
		setOnTouchListener(touchListener);
		rootContainer.internal_setGameView(this);
	}
	

	private void redraw(){
		Canvas canvas = getHolder().lockCanvas();
		if (canvas!=null) {
			canvas.drawColor(getGameViewBackground());
			rootContainer.draw(canvas);
			getHolder().unlockCanvasAndPost(canvas);
		}
		
		enterFrame.dispatch(GameView.this,GameViewEvent.alloc().init(GameViewEvent.ENTER_FRAME));
	}

	private void startTimer(){
		
		if (timerTask==null) {
			timerTask = new TimerTask() {

				@Override
				public void run() {
					redraw();
				}
			};
			
			timer.schedule(timerTask, 1000/getFps(), 1000/getFps());
		}
	}

	private void stopTimer(){
		if (timerTask!=null) {
			timerTask.cancel();
			timerTask=null;
		}
	}

	public int getFps() {
		return fps;
	}


	/**
	 * 帧频最小值为1，如果传入参数小于1，则系统自动使用1作为帧频
	 * @param fps
	 */
	public void setFps(int fps) {
		if (fps<1) {
			fps=1;
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
	
	public Root getRoot() {
		return rootContainer;
	}

	private final Timer timer = new Timer();
	private TimerTask timerTask =null;
	private int fps = 20;
	private boolean running = false;
	private int gameViewBackground = Color.WHITE;
	private final Root rootContainer = new Root();

	public final EventListenerList<GameViewEvent> enterFrame = new EventListenerList<GameViewEvent>();
	public final EventListenerList<GameViewEvent> surfaceDestroyed = new EventListenerList<GameViewEvent>();
	public final EventListenerList<GameViewEvent> surfaceCreated = new EventListenerList<GameViewEvent>();
	public final EventListenerList<GameViewEvent> surfaceChanged = new EventListenerList<GameViewEvent>();
	public final EventListenerList<G2DMotionEvent> touch = new EventListenerList<G2DMotionEvent>();
	public final EventListenerList<G2DMotionEvent> touchMove = new EventListenerList<G2DMotionEvent>();
	public final EventListenerList<G2DMotionEvent> touchDown = new EventListenerList<G2DMotionEvent>();
	public final EventListenerList<G2DMotionEvent> touchUp = new EventListenerList<G2DMotionEvent>();

	private final Callback callback=new Callback() {

		@Override
		public void surfaceDestroyed(SurfaceHolder holder) {
			stopTimer();
			surfaceDestroyed.dispatch(GameView.this, GameViewEvent.alloc().init(GameViewEvent.SURFACE_DESTROYED));
			running=false;
		}

		@Override
		public void surfaceCreated(SurfaceHolder holder) {
			running=true;
			surfaceCreated.dispatch(GameView.this, GameViewEvent.alloc().init(GameViewEvent.SURFACE_CREATED));
			
			redraw();
			startTimer();
		}

		@Override
		public void surfaceChanged(SurfaceHolder holder, int format, int width,
				int height) {
			surfaceChanged.dispatch(GameView.this, GameViewEvent.alloc().init(GameViewEvent.SURFACE_CHANGED));
		}
	};
	
	
	private final OnTouchListener touchListener=new OnTouchListener() {
		
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			switch (event.getAction()) {
			case MotionEvent.ACTION_MOVE:
				rootContainer.internal_dispatchTouchEvent(TouchEvent.alloc(TouchEvent.class).init(TouchEvent.TOUCH_MOVE, rootContainer, event));
				touch.dispatch(GameView.this, PObject.alloc(G2DMotionEvent.class).init(G2DMotionEvent.TOUCH_MOVE, event));
				touchMove.dispatch(GameView.this, com.plter.lib.java.lang.PObject.alloc(G2DMotionEvent.class).init(G2DMotionEvent.TOUCH_MOVE, event));
				break;
			case MotionEvent.ACTION_DOWN:
				rootContainer.internal_dispatchTouchEvent(TouchEvent.alloc(TouchEvent.class).init(TouchEvent.TOUCH_DOWN, rootContainer, event));
				touch.dispatch(GameView.this, PObject.alloc(G2DMotionEvent.class).init(G2DMotionEvent.TOUCH_DOWN, event));
				touchDown.dispatch(GameView.this, PObject.alloc(G2DMotionEvent.class).init(G2DMotionEvent.TOUCH_DOWN, event));
				break;
			case MotionEvent.ACTION_UP:
				rootContainer.internal_dispatchTouchEvent(TouchEvent.alloc(TouchEvent.class).init(TouchEvent.TOUCH_UP, rootContainer, event));
				touch.dispatch(GameView.this, PObject.alloc(G2DMotionEvent.class).init(G2DMotionEvent.TOUCH_UP, event));
				touchUp.dispatch(GameView.this, PObject.alloc(G2DMotionEvent.class).init(G2DMotionEvent.TOUCH_UP, event));
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
