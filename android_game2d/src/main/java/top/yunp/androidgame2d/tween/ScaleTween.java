package top.yunp.androidgame2d.tween;

import top.yunp.androidgame2d.display.Display;

public class ScaleTween extends Tween {


    public ScaleTween(Display target, float startScaleX, float startScaleY, float endScaleX, float endScaleY) {
        this.setStartX(startScaleX);
        this.setStartScaleY(startScaleY);
        this.setEndScaleX(endScaleX);
        this.setEndScaleY(endScaleY);
        setTarget(target);
    }

    @Override
    protected void onEnterFrame(int currentFrame, int frames) {
        if (speedX != 0) {
            getTarget().setScaleX(getStartScaleX() + speedX * currentFrame);
        }
        if (speedY != 0) {
            getTarget().setScaleY(getStartScaleY() + speedY * currentFrame);
        }
    }

    public float getStartScaleX() {
        return startScaleX;
    }

    public void setStartX(float startScaleX) {
        this.startScaleX = startScaleX;
        computeScaleXDistancePerFrame();
    }

    public float getStartScaleY() {
        return startScaleY;
    }

    public void setStartScaleY(float startScaleY) {
        this.startScaleY = startScaleY;
        computeScaleYDistancePerFrame();
    }

    public float getEndScaleX() {
        return endScaleX;
    }

    public void setEndScaleX(float endScaleX) {
        this.endScaleX = endScaleX;
        computeScaleXDistancePerFrame();
    }

    public float getEndScaleY() {
        return endScaleY;
    }

    public void setEndScaleY(float endScaleY) {
        this.endScaleY = endScaleY;
        computeScaleYDistancePerFrame();
    }

    @Override
    public void setFrames(int frames) {
        super.setFrames(frames);
        computeScaleXDistancePerFrame();
        computeScaleYDistancePerFrame();
    }

    protected void onEnd() {
        getTarget().setScaleX(endScaleX);
        getTarget().setScaleY(endScaleY);
    }

    ;

    private void computeScaleXDistancePerFrame() {
        speedX = ((float) (endScaleX - startScaleX)) / getFrames();
    }

    private void computeScaleYDistancePerFrame() {
        speedY = ((float) (endScaleY - startScaleY)) / getFrames();
    }

    private float startScaleX = 1, startScaleY = 1, endScaleX = 1, endScaleY = 1, speedX = 0, speedY = 0;

}
