package com.plter.game2d.display;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.plter.game2d.geom.Point;
import com.plter.game2d.lang.Array;

class Game2DHandler extends Handler {

	@Override
	public void handleMessage(Message msg) {
		Bundle data = msg.getData();
		
		switch (msg.what) {
		case MESSAGE_TYPE_NATIVE_TOUCH_CALL_BACK:
			Array<Point> touches = new Array<Point>();
			float[] touchesXY = data.getFloatArray(KEY_TOUCHES_XY_FLOAT_ARRAY);
			for (int i = 0; i < touchesXY.length; i+=2) {
				touches.push(new Point(touchesXY[i], touchesXY[i+1]));
			}
			
			Game2DActivity.getGame2dActivity().getStage().onTouchHandler(data.getString(KEY_TOUCH_TYPE), touches);
			break;
		case MESSAGE_TYPE_NATIVE_KEY_PRESS_CALL_BACK:
			Game2DActivity.getGame2dActivity().getStage().onKeyEventHandler(data.getString(KEY_KEY_PRESS_TYPE), data.getInt(KEY_KEY_PRESS_CODE));
			break;
		}
	}
	
	public static final int MESSAGE_TYPE_NATIVE_TOUCH_CALL_BACK = 10000;
	public static final int MESSAGE_TYPE_NATIVE_KEY_PRESS_CALL_BACK = 10001;
	
	public static final String KEY_TOUCHES_XY_FLOAT_ARRAY = "keyTouchesXYFloatArray";
	public static final String KEY_TOUCH_TYPE = "keyTouchType";
	public static final String KEY_KEY_PRESS_TYPE = "keyPressType";
	public static final String KEY_KEY_PRESS_CODE = "keyPressCode";
}
