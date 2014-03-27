package com.plter.game2d.display;

import com.plter.game2d.geom.Size;

public final class Stage extends DisplayObjectContainer {

	Stage() {
		setNativeObject(createNativeObject());
	}
	
	protected final native long createNativeObject();
	
	public final native void setDisplayStats(boolean b);
	public final native void setAnimationInterval(float interval);
	
	public Size getVisibleSize(){
		float[] fs = nativeGetVisibleSize();
		stageSize.setWidth(fs[0]);
		stageSize.setHeight(fs[1]);
		return stageSize;
	}
	private final Size stageSize = new Size(0, 0);
	private native float[] nativeGetVisibleSize();
	
	public void setDesignResolutionSize(float width,float height,ResolutionPolicy resolutionPolicy){
		int policy = 0;
		switch (resolutionPolicy) {
		case EXACT_FIT:
			policy = 1;
			break;
		case FIXED_HEIGHT:
			policy = 2;
			break;
		case FIXED_WIDTH:
			policy = 3;
			break;
		case NO_BORDER:
			policy = 4;
			break;
		case SHOW_ALL:
			policy = 5;
			break;
		case UNKNOWN:
			policy = 0;
			break;
		}
		
		nativeSetDesignResolutionSize(width, height, policy);
	}
	private native void nativeSetDesignResolutionSize(float width,float height,int resolutionPolicy);
}
