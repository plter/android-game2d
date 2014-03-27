package com.plter.game2d.geom;



public class Rect {
	
	
	public Rect(float x,float y,float width,float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	

	private float x,y,width,height;

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
	
	public boolean containsPoint(Point p){
		return p.getX()>getX()&&
				p.getY()>getY()&&
				p.getX()<getX()+getWidth()&&
				p.getY()<getY()+getHeight();
	}
	
	@Override
	public String toString() {
		return "[Rect(x="+getX()+",y="+getY()+",width="+getWidth()+",height="+getHeight()+")]";
	}
}
