package com.plter.android.game2d.events;

import com.plter.lib.java.event.Event;
import com.plter.lib.java.lang.ObjectPool;

public class GameViewEvent extends Event {
	
	
	public static final String SURFACE_CREATED = "surfaceCreated";
	public static final String SURFACE_DESTROYED = "surfaceDestroyed";
	public static final String SURFACE_CHANGED = "surfaceChanged";
	public static final String ENTER_FRAME = "enterFrame";

	
	public static final GameViewEvent alloc(){
		return ObjectPool.get(GameViewEvent.class);
	}
	
	@Override
	public GameViewEvent init(String type) {
		return (GameViewEvent) super.init(type);
	}
	
	@Override
	public GameViewEvent clone() {
		return alloc(GameViewEvent.class).init(getType());
	}
}
