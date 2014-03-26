package com.plter.android.game2d.events;

import android.view.MotionEvent;

import com.plter.lib.java.event.Event;

public class G2DMotionEvent extends Event {
	
	public static final String TOUCH_DOWN = "touchDown";
	public static final String TOUCH_MOVE = "touchMove";
	public static final String TOUCH_UP = "touchUp";

	public G2DMotionEvent init(String type,MotionEvent relatedMotionEvent) {
		super.init(type);
		this.relatedMotionEvent=relatedMotionEvent;
		return this;
	}
	
	@Override
	public G2DMotionEvent clone() {
		return alloc(G2DMotionEvent.class).init(getType(), getRelatedMotionEvent());
	}
	
	public MotionEvent getRelatedMotionEvent() {
		return relatedMotionEvent;
	}
	
	private MotionEvent relatedMotionEvent = null;
}
