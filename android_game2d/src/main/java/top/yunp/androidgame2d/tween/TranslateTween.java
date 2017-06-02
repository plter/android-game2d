package top.yunp.androidgame2d.tween;

import top.yunp.androidgame2d.display.Display;

public class TranslateTween extends Tween {


    public TranslateTween() {
    }


    public TranslateTween(Display target, float startX, float startY, float endX, float endY) {
        this.setStartX(startX);
        this.setStartY(startY);
        this.setEndX(endX);
        this.setEndY(endY);
        setTarget(target);
    }

    @Override
    protected void onEnterFrame(int currentFrame, int frames) {
        if (distanceXPerFrame != 0) {
            getTarget().setX(getStartX() + distanceXPerFrame * currentFrame);
        }
        if (distanceYPerFrame != 0) {
            getTarget().setY(getStartY() + distanceYPerFrame * currentFrame);
        }
    }

    public float getStartX() {
        return startX;
    }

    public void setStartX(float startX) {
        this.startX = startX;
        computeDistanceXPerFrame();
    }

    public float getStartY() {
        return startY;
    }

    public void setStartY(float startY) {
        this.startY = startY;
        computeDistanceYPerFrame();
    }

    public float getEndX() {
        return endX;
    }

    public void setEndX(float endX) {
        this.endX = endX;
        computeDistanceXPerFrame();
    }

    public float getEndY() {
        return endY;
    }

    public void setEndY(float endY) {
        this.endY = endY;
        computeDistanceYPerFrame();
    }

    @Override
    public void setFrames(int frames) {
        super.setFrames(frames);
        computeDistanceXPerFrame();
        computeDistanceYPerFrame();
    }

    protected void onEnd() {
        getTarget().setX(endX);
        getTarget().setY(endY);
    }

    ;

    private void computeDistanceXPerFrame() {
        distanceXPerFrame = ((float) (endX - startX)) / getFrames();
    }

    private void computeDistanceYPerFrame() {
        distanceYPerFrame = ((float) (endY - startY)) / getFrames();
    }

    private float startX = 0, startY = 0, endX = 0, endY = 0, distanceXPerFrame = 0, distanceYPerFrame = 0;

}
