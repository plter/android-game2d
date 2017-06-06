package top.yunp.androidgame2d.display;

import android.graphics.Paint;

/**
 * Created by plter on 6/6/17.
 */

public class RectangleStroke extends Rectangle {


    public RectangleStroke(float width, float height, int color) {
        super(width, height, color);

        getPaint().setStyle(Paint.Style.STROKE);
    }
}
