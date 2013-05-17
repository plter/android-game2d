package com.plter.balls;

import android.graphics.Path.Direction;

import com.plter.android.game2d.display.Shape;

public class Ball extends Shape {

	
	private float speedX=0,speedY=0;
	
	public Ball() {
		
		speedX = (float) (Math.random()*5+1);
		speedY = (float) (Math.random()*5+1);
		
		getPaint().setColor(0xFF666666);
		getPath().addCircle(0, 0, 30, Direction.CCW);
	}
	
	public void move(){
		x+=speedX;
		y+=speedY;
		
		if (x<0) {
			speedX=Math.abs(speedX);
		}
		if (y<0) {
			speedY=Math.abs(speedY);
		}
		if (x>800) {
			speedX=-Math.abs(speedX);
		}
		if (y>500) {
			speedY=-Math.abs(speedY);
		}
	}
}
