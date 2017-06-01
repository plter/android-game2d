package top.yunp.e006balls;

import top.yunp.androidgame2d.display.Container;

public class Main extends Container {

	
	private Ball[] balls;
	private Ball ball;
	
	public Main() {
		
		balls = new Ball[100];
		
		for (int i = 0; i < balls.length; i++) {
			ball = new Ball();
			add(ball);
			balls[i]=ball;
		}
	}
	
	public void move(){
		for (int i = 0; i < balls.length; i++) {
			balls[i].move();
		}
	}
}
