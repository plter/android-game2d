package com.plter.game2d.events;

import com.plter.game2d.geom.Point;
import com.plter.game2d.lang.Array;

public class TouchEvent extends Event {
	
	public static final String TOUCH_BEGAN = "touchBegan";
	public static final String TOUCH_MOVED = "touchMoved";
	public static final String TOUCH_ENDED = "touchEnded";

	public TouchEvent(String name,Array<Point> touches) {
		super(name);
		
		this.touches = touches;
	}
	
	public int getTouchesCount(){
		return touches!=null?touches.length():0;
	}
	
	public Point getGlobalTouchPosition(){
		return touches.get(0);
	}
	
	public Array<Point> getTouches() {
		return touches;
	}
	private Array<Point> touches = null; 
	
	@Override
	public TouchEvent clone() {
		return new TouchEvent(getName(), getTouches());
	}

}
