package com.plter.game2d.display;

import com.plter.game2d.events.EventListenerList;
import com.plter.game2d.events.TouchEvent;
import com.plter.game2d.geom.Point;
import com.plter.game2d.lang.Array;
import com.plter.game2d.lang.ArrayItem;
import com.plter.game2d.lang.ArrayLoopCallback;

public abstract class InteractiveObject extends DisplayObject {

	
	void onTouchHandler(final String type,final Array<Point> touces){
		
		onTouch.dispatch(new TouchEvent(type, touces), this);
		
		final Point p = touces.get(0);
		if (p==null) {
			return;
		}
				
		if (this instanceof DisplayObjectContainer) {
			((DisplayObjectContainer)this).getChildren().reverseEach(new ArrayLoopCallback<DisplayObject>() {
				
				@Override
				public void onRead(DisplayObject currentValue,
						ArrayItem<DisplayObject> currentItem) {
					
					if (currentValue instanceof InteractiveObject&&
							currentValue.isVisible()&&
							currentValue.getGlobalBoundingBox().containsPoint(p)) {
						
						((InteractiveObject)currentValue).onTouchHandler(type, touces);
						
						break_();
					}
				}
			});
		}
	}
	
	public final EventListenerList<TouchEvent> onTouch = new EventListenerList<TouchEvent>();
}
