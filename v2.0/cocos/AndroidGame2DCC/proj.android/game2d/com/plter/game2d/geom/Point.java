package com.plter.game2d.geom;

public class Point {
	
	
	public Point(float x,float y) {
		this.x = x;
		this.y = y;
	}

	private float x,y;

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
	@Override
	public String toString() {
		return "[Point(x="+getX()+",y="+getY()+")]";
	}
}
