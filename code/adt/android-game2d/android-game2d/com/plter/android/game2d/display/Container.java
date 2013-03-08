package com.plter.android.game2d.display;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Canvas;
import android.graphics.RectF;

import com.plter.android.game2d.events.TouchEvent;

public class Container extends Display implements IContainer{

	@Override
	public Display add(Display display) {
		displays.add(display);
		display.internal_setParent(this);
		return display;
	}

	@Override
	public Display addAt(Display display, int index) {
		displays.add(index, display);
		display.internal_setParent(this);
		return display;
	}

	@Override
	public boolean remove(Display display) {
		display.internal_setParent(null);
		return displays.remove(display);
	}

	@Override
	public Display remove(int index) {
		get(index).internal_setParent(null);
		return displays.remove(index);
	}

	@Override
	public Display get(int index) {
		return displays.get(index);
	}

	@Override
	public void removeAll() {
		while (displays.size()>0) {
			displays.remove(0).internal_setParent(null);
		}
	}

	@Override
	public void swap(Display display, Display display2) {
		swap(getIndex(display), getIndex(display2));
	}

	@Override
	public void swap(int index, int index2) {
		if (index<=-1||index2<=-1) {
			return;
		}
		
		int min,max;
		
		if (index<index2) {
			min = index;
			max = index2;
		}else if (index>index2) {
			min = index2;
			max = index;
		}else{
			return;
		}
		
		displays.add(min, displays.remove(max));
		displays.add(max, displays.remove(index+1));
	}
	
	private final List<Display> displays = new ArrayList<Display>();

	@Override
	public int getIndex(Display display) {
		for (int i = 0; i < displays.size(); i++) {
			if (displays.get(i)==display) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public final void draw(Canvas canvas) {
		Display d;
		for (int i = 0; i < displays.size(); i++) {
			d = displays.get(i);
			if (d.visible) {
				d.internal_draw(canvas);
			}
		}
	}
	
	@Override
	void internal_dispatchTouchEvent(TouchEvent e) {
		
		Display d;
		
		for (int i = displays.size()-1; i >= 0; i--) {
			try{
				d = displays.get(i);
				if (d.isTouchEnable()&&d.hitTest(e.getX(), e.getY())) {
					d.internal_dispatchTouchEvent(TouchEvent.alloc(TouchEvent.class).init(e.getType(), d, e.getRelatedMotionEvent()));
					break;
				}
			}catch(ArrayIndexOutOfBoundsException exception){
			}
		}
		
		super.internal_dispatchTouchEvent(e);
	}
	
	@Override
	public RectF getBounds() {
		bounds.setEmpty();
		for (int i = 0; i < displays.size(); i++) {
			bounds.union(displays.get(i).getBounds());
		}
		getBoundsMatrix().mapRect(bounds);
		return bounds;
	}
	private final RectF bounds = new RectF();
}
