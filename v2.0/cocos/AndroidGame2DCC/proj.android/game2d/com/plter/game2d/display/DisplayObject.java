package com.plter.game2d.display;

import com.plter.game2d.geom.Point;
import com.plter.game2d.geom.Rect;


public abstract class DisplayObject {
	
	
	public DisplayObject() {
	}

	private long nativeObject = 0;
	
	final long getNativeObject(){
		return nativeObject;
	}
	final void setNativeObject(long p){
		nativeObject = p;
	}
	
	private boolean visible = true;
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
		nativeSetVisible(nativeObject,visible);
	}
	private native void nativeSetVisible(long nativeObject,boolean b);
	
	private float rotation = 0;
	public float getRotation() {
		return rotation;
	}
	public void setRotation(float rotation) {
		this.rotation = rotation;
		nativeSetRotation(nativeObject,rotation);
	}
	private native void nativeSetRotation(long nativeObject,float r);
	
	
	public Rect getGlobalBoundingBox(){
		getBoundingBox();
		
		globalBoundingBox.setX(getParent()!=null?getParent().getGlobalPositionX()+boundingBox.getX():boundingBox.getX());
		globalBoundingBox.setY(getParent()!=null?getParent().getGlobalPositionY()+boundingBox.getY():boundingBox.getY());
		globalBoundingBox.setWidth(boundingBox.getWidth());
		globalBoundingBox.setHeight(boundingBox.getHeight());
		
		return globalBoundingBox;
	}
	private final Rect globalBoundingBox = new Rect(0, 0, 0, 0);
	
	public Rect getBoundingBox(){
		float[] fr = nativeGetBoundingBox(nativeObject);
		boundingBox.setX(fr[0]);
		boundingBox.setY(fr[1]);
		boundingBox.setWidth(fr[2]);
		boundingBox.setHeight(fr[3]);
		
		return boundingBox;
	}
	private final Rect boundingBox = new Rect(0, 0, 0, 0);
	private native float[] nativeGetBoundingBox(long nativeObject);
	
	
	public void setPosition(Point p){
		setPosition(p.getX(), p.getY());
	}
	public void setPosition(float x,float y){
		position.setX(x);
		position.setY(y);
		nativeSetPosition(getNativeObject(), x, y);
	}
	public Point getPosition(){
		return position;
	}
	public float getPositionX(){
		return position.getX();
	}
	public float getPositionY(){
		return position.getY();
	}
	public void setPositionX(float x){
		position.setX(x);
		nativeSetPositionX(getNativeObject(), x);
	}
	public void setPositionY(float y){
		position.setY(y);
		nativeSetPositionY(getNativeObject(), y);
	}
	private final Point position = new Point(0, 0);
	private native void nativeSetPosition(long nativeThis,float x,float y);
	private native void nativeSetPositionX(long nativeThis,float x);
	private native void nativeSetPositionY(long nativeThis,float y);
	
	public Point getGlobalPosition(){
		globalPosition.setX(getGlobalPositionX());
		globalPosition.setY(getGlobalPositionY());
		return globalPosition;
	}
	private final Point globalPosition = new Point(0, 0);
	public float getGlobalPositionX(){
		if (getParent()!=null) {
			return getParent().getGlobalPositionX()+getPositionX();
		}else{
			return getPositionX();
		}
	}
	public float getGlobalPositionY(){
		if (getParent()!=null) {
			return getParent().getGlobalPositionY()+getPositionY();
		}else{
			return getPositionY();
		}
	}
	
	public void setAnchorPoint(float x,float y){
		anchorPoint.setX(x);
		anchorPoint.setY(y);
		nativeSetAnchorPoint(getNativeObject(), x, y);
	}
	private final Point anchorPoint = new Point(0.5f, 0.5f);
	private native void nativeSetAnchorPoint(long nativeThis,float x,float y);
	
	private DisplayObjectContainer parent = null;
	public DisplayObjectContainer getParent() {
		return parent;
	}
	void setParent(DisplayObjectContainer parent) {
		this.parent = parent;
	}
	
	public void removeFromParent(){
		if (getParent()!=null) {
			getParent().removeChild(this);
		}
	}
	
	public Stage getStage(){
		if (getParent()!=null) {
			return getParent().getStage();
		}else if(this instanceof Stage){
			return (Stage) this;
		}else{
			return null;
		}
	}
}
