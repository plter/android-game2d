package com.plter.android.game2d.events;

import com.plter.lib.java.event.Event;

public class TweenEvent extends Event {

	
	public static final String TWEEN_START="tweenStart";
	public static final String TWEEN_END="tweenEnd";
	
	@Override
	public TweenEvent init(String type) {
		super.init(type);
		return this;
	}
	
	@Override
	public TweenEvent clone() {
		return alloc(TweenEvent.class).init(getType());
	}
}
