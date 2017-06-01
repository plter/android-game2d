package top.yunp.androidgame2d.tween;

import top.yunp.androidgame2d.display.Display;

public class AlphaTween extends Tween {


    public AlphaTween() {
        computeDistancePerFrame();
    }

    public AlphaTween(Display target, float startAlpha, float endAlpha) {
        setTarget(target);
        setStartAlpha(startAlpha);
        setEndAlpha(endAlpha);
    }


    @Override
    protected void onEnterFrame(int currentFrame, int frames) {
        getTarget().setAlpha(startAlpha + currentFrame * distancePerFrame);
    }


    public float getEndAlpha() {
        return endAlpha;
    }


    public void setEndAlpha(float endAlpha) {
        this.endAlpha = endAlpha;
        computeDistancePerFrame();
    }


    public float getStartAlpha() {
        return startAlpha;
    }


    public void setStartAlpha(float startAlpha) {
        this.startAlpha = startAlpha;
        computeDistancePerFrame();
    }

    @Override
    protected void onEnd() {
        getTarget().setAlpha(endAlpha);
    }

    private void computeDistancePerFrame() {
        distancePerFrame = (endAlpha - startAlpha) / getFrames();
    }

    private float startAlpha = 0, endAlpha = 1, distancePerFrame = 0.5f;

}
