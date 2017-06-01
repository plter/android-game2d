package top.yunp.androidgame2d.tween;

import top.yunp.androidgame2d.display.Display;

public class RotateTween extends Tween {
	
	
	public RotateTween() {
		computeAngleDistancePerFrame();
	}
	
	public RotateTween(Display target,float startAngle,float endAngle) {
		setTarget(target);
		setStartAlpha(startAngle);
		setEndAngle(endAngle);
	}
	

	@Override
	protected void onEnterFrame(int currentFrame, int frames) {
		getTarget().setAlpha(startAngle+currentFrame*angleDistancePerFrame);
	}
	
	
	public float getEndAngle() {
		return endAlpha;
	}


	public void setEndAngle(float endAlpha) {
		this.endAlpha = endAlpha;
		computeAngleDistancePerFrame();
	}


	public float getStartAlpha() {
		return startAngle;
	}


	public void setStartAlpha(float startAlpha) {
		this.startAngle = startAlpha;
		computeAngleDistancePerFrame();
	}
	
	@Override
	protected void onEnd() {
		getTarget().setAlpha(endAlpha);
	}

	private void computeAngleDistancePerFrame(){
		angleDistancePerFrame=(endAlpha-startAngle)/getFrames();
	}

	private float startAngle=0,endAlpha=1,angleDistancePerFrame=0.5f;

}
