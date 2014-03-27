package com.plter.game2d.display;

import com.plter.game2d.geom.Size;

public class Sprite extends DisplayObjectContainer {
	
	public Sprite() {
		setNativeObject(createNativeObject());
	}

	protected final native long createNativeObject();
	
	public void setContentSize(Size s){
		setContentSize(s.getWidth(),s.getHeight());
	}
	public void setContentSize(float width,float height){
		nativeSetContentSize(getNativeObject(), width, height);
	}
	private native void nativeSetContentSize(long nativeThis,float width,float height);
}
