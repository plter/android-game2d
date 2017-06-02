package top.yunp.androidgame2d.display;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;

public class Shape extends Display {

	@Override
	public final void draw(Canvas canvas) {
		canvas.drawPath(path, paint);
	}

	@Override
	public RectF getBounds() {
		bounds.setEmpty();
		path.computeBounds(bounds, false);
		getBoundsMatrix().mapRect(bounds);
		return bounds;
	}
	
	
	public Path getPath() {
		return path;
	}
	
	public Paint getPaint() {
		return paint;
	}
	
	@Override
	public Display setAlpha(float alpha) {
		paint.setAlpha((int) (255*alpha));
		return super.setAlpha(alpha);
	}
	
	private final RectF bounds = new RectF();
	private final Path path = new Path();
	private final Paint paint = new Paint();

}
