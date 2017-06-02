package top.yunp.androidgame2d.display;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;

public class TextLine extends Display {

	public TextLine() {
	}
	
	public TextLine(String text) {
		setText(text);
	}
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
		lineHeight=getTextBounds().height();
	}

	private String text = "text";

	@Override
	public void draw(Canvas canvas) {
		canvas.drawText(getText(), 0, 0,paint);
	}

	@Override
	public RectF getBounds() {
		textBounds.setEmpty();
		bounds.set(getTextBounds());
		getBoundsMatrix().mapRect(bounds);
		return bounds;
	}
	
	public void setSize(int size){
		paint.setTextSize(size);
		lineHeight=getTextBounds().height();
	}
	
	public void setFont(String font){
		setFont(font, paint.getTypeface().getStyle());
		lineHeight=getTextBounds().height();
	}
	
	public void setFont(String font,int style){
		paint.setTypeface(Typeface.create(font, style));
		lineHeight=getTextBounds().height();
	}
	
	public Rect getTextBounds(){
		paint.getTextBounds(getText(), 0, getText().length(), textBounds);
		return textBounds;
	}
	
	
	@Override
	public Display setAlpha(float alpha) {
		paint.setAlpha((int) (255*alpha));
		return super.setAlpha(alpha);
	}
	
	public Paint getPaint() {
		return paint;
	}
	
	public int getLineHeight() {
		return lineHeight;
	}
	
	private final Rect textBounds = new Rect();
	private final RectF bounds = new RectF();
	private final Paint paint = new Paint();
	private int lineHeight = 20;
	
}
