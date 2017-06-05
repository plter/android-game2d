package top.yunp.androidgame2d.display;

import android.graphics.Path;

/**
 * Created by plter on 6/5/17.
 */

public class Rectangle extends Shape {

    private float width = 100, height = 100;
    private int color = 0xff000000;
    private int borderWidth = 0;
    private int borderColor = 0xffffffff;

    public Rectangle(float width, float height, int color) {
        setWidth(width);
        setHeight(height);
        setColor(color);
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
        refreshRectanglePath();
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
        refreshRectanglePath();
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
        getPaint().setColor(color);
    }

    private void refreshRectanglePath() {
        getPath().reset();
        getPath().addRect(getBorderWidth(), getBorderWidth(), getWidth() - getBorderWidth(), getHeight() - getBorderWidth(), Path.Direction.CW);
        getPath().close();
    }

    public int getBorderWidth() {
        return borderWidth;
    }

    public void setBorderWidth(int borderWidth) {
        this.borderWidth = borderWidth;
        refreshRectanglePath();
    }

    public int getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(int borderColor) {
        this.borderColor = borderColor;

        //TODO implement the border color
    }
}
