package com.plter.game2d.display;

import com.plter.game2d.lang.Array;

public abstract class DisplayObjectContainer extends InteractiveObject {

	/**
	 * Add a DisplayObject to this container,if this container contains the DisplayObject or the DisplayObject already has a parent, it will do nothing
	 * @param child
	 */
	public void addChild(DisplayObject child){
		if (child.getParent()==null&&!contains(child)) {
			children.push(child);
			child.setParent(this);
			nativeAddChild(getNativeObject(), child.getNativeObject());
		}
	}
	private native void nativeAddChild(long nativeThis,long child);
	
	public boolean contains(DisplayObject child){
		return children.contains(child);
	}
	
	public void removeChild(DisplayObject child){
		if (contains(child)) {
			children.remove(child);
			child.setParent(null);
			nativeRemoveChild(getNativeObject(), child.getNativeObject());
		}
	}
	private native void nativeRemoveChild(long nativeThis,long child);
	
	private final Array<DisplayObject> children = new Array<DisplayObject>();
	public Array<DisplayObject> getChildren() {
		return children;
	}
}
