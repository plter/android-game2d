package com.plter.game2d.display;

import com.plter.game2d.geom.Rect;

public class Image extends InteractiveObject {

	public Image(String fileName) {
		setNativeObject(createNativeObjectWithFileName(fileName));
	}
	
	public Image(String fileName,Rect rect) {
		setNativeObject(createNativeObjectWithFileNameAndRect(fileName, rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight()));
	}
	
	private native long createNativeObjectWithFileNameAndRect(String name,float x,float y,float width,float height);
	private native long createNativeObjectWithFileName(String fileName);
	
}
