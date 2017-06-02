package top.yunp.androidgame2d.display;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;

public class Image extends Display {

    public Image(Bitmap bitmap) {
        setBitmap(bitmap);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, src, dst, getPaint());
    }

    @Override
    public RectF getBounds() {
        bounds.setEmpty();
        getBoundsMatrix().mapRect(bounds, dst);
        return bounds;
    }

    private final RectF bounds = new RectF();

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;

        width = bitmap.getWidth();
        height = bitmap.getHeight();

        src.setEmpty();
        src.right = bitmap.getWidth();
        src.bottom = bitmap.getHeight();

        dst.setEmpty();
        dst.right = width;
        dst.bottom = height;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
        dst.right = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
        dst.bottom = height;
    }

    @Override
    public Display setAlpha(float alpha) {
        paint.setAlpha((int) (255 * alpha));
        return super.setAlpha(alpha);
    }

    public Paint getPaint() {
        return paint;
    }

    private Bitmap bitmap = null;
    private float width = 100, height = 100;
    private final RectF dst = new RectF(0, 0, 100, 100);
    private final Rect src = new Rect();
    private final Paint paint = new Paint();
}
