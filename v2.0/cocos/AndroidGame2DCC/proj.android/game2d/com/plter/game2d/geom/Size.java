package com.plter.game2d.geom;

public class Size {

	public Size(float width,float height) {
		this.width = width;
		this.height = height;
	}
	
	private float width,height;

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}
	
	@Override
	public String toString() {
		return "[Size(width="+getWidth()+",height="+getHeight()+")]";
	}
}
