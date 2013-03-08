package com.plter.android.game2d.tween;

import com.plter.android.game2d.display.Display;
import com.plter.android.game2d.display.GameView;
import com.plter.android.game2d.events.GameViewEvent;
import com.plter.android.game2d.events.TweenEvent;
import com.plter.lib.java.event.EventListener;
import com.plter.lib.java.event.EventListenerList;
import com.plter.lib.java.lang.PObject;

public abstract class Tween {
	
	public Tween() {
	}
	
	public Tween(Display target) {
		setTarget(target);
	}

	private Display target=null;

	public Display getTarget() {
		return target;
	}

	public Tween setTarget(Display target) {
		this.target = target;
		return this;
	}
	
	public Tween start(){
		if (running||target==null) {
			return this;
		}
		GameView gv = target.getGameView();
		if (gv==null) {
			return this;
		}
		
		onStart();
		running=true;
		tweenStart.dispatch(this, PObject.alloc(TweenEvent.class).init(TweenEvent.TWEEN_START));
		currentFrame=0;
		gv.enterFrame.add(enterFrameHandler);
		return this;
	}
	
	protected abstract void onEnterFrame(int currentFrame,int frames);
	protected void onStart(){}
	protected void onEnd(){}
	
	public int getFrames() {
		return frames;
	}

	public void setFrames(int frames) {
		this.frames = frames;
	}

	private int frames = 20,currentFrame=0;
	
	private final EventListener<GameViewEvent> enterFrameHandler=new EventListener<GameViewEvent>() {
		
		@Override
		public boolean onReceive(Object target, GameViewEvent event) {
			currentFrame++;
			onEnterFrame(currentFrame,frames);
			if (currentFrame>=frames) {
				getTarget().getGameView().enterFrame.remove(enterFrameHandler);
				
				running=false;
				onEnd();
				tweenEnd.dispatch(this, PObject.alloc(TweenEvent.class).init(TweenEvent.TWEEN_END));
			}
			return false;
		}
	};
	
	public boolean isRunning() {
		return running;
	}
	
	private boolean running=false;
	
	public final EventListenerList<TweenEvent> tweenStart = new EventListenerList<TweenEvent>();
	public final EventListenerList<TweenEvent> tweenEnd = new EventListenerList<TweenEvent>();
}
