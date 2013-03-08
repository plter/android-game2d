

package com.plter.android.game2d.events;

import android.view.MotionEvent;

import com.plter.android.game2d.display.Display;
import com.plter.lib.java.event.Event;

/**
 * 
 * @author xtiqin,plter.com 
 *
 */
public class TouchEvent extends Event {
	
	
	public static final String TOUCH_DOWN = "touchDown";
	public static final String TOUCH_MOVE = "touchMove";
	public static final String TOUCH_UP = "touchUp";
	

	public TouchEvent init(String type,Display target,MotionEvent relatedMotionEvent) {
		this.relatedMotionEvent=relatedMotionEvent;
		this.target=target;
		return (TouchEvent) super.init(type);
	}
	
	public float getX(){
		return getGlobalX()-target.getGlobalX();
	}
	
	public float getY(){
		return getGlobalY()-target.getGlobalY();
	}
	
	public float getX(int index){
		return getGlobalX(index)-target.getGlobalX();
	}
	
	public float getY(int index){
		return getGlobalY(index)-target.getGlobalY();
	}
	
	public float getGlobalX(){
		return relatedMotionEvent.getX();
	}
	
	public float getGlobalY(){
		return relatedMotionEvent.getY();
	}
	
	public float getGlobalX(int index){
		return relatedMotionEvent.getX(index);
	}
	
	public float getGlobalY(int index){
		return relatedMotionEvent.getY(index);
	}
	
	public float getPointerCount(){
		return relatedMotionEvent.getPointerCount();
	}
	
	public MotionEvent getRelatedMotionEvent(){
		return relatedMotionEvent;
	}
	
	@Override
	public TouchEvent clone() {
		return alloc(TouchEvent.class).init(getType(), target, relatedMotionEvent);
	}
	
	
	@Override
	public void recycle() {
		relatedMotionEvent=null;
		target=null;
		super.recycle();
	}
	
	private MotionEvent relatedMotionEvent = null;
	private Display target=null;	
}
