package com.plter.game2d.display;

import com.plter.game2d.events.EventListenerList;
import com.plter.game2d.events.Game2DActivityEvent;

import android.app.NativeActivity;
import android.os.Bundle;
import android.os.Message;

// The name of .so is specified in AndroidMenifest.xml. NativityActivity will load it automatically for you.
// You can use "System.loadLibrary()" to load other .so files.

public class Game2DActivity extends NativeActivity{
	
	
	public Game2DActivity() {
		game2dActivity = this;
	}
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	protected void onGame2DCreationComplete(){
	}
	
	private Stage stage = null;
	public Stage getStage() {
		return stage;
	}
	
	
	/**
	 * this method will be called by native cpp code
	 */
	@SuppressWarnings("unused")
	private final static void nativeCreationCompleteCallback(){
		
		//init some data
		getGame2dActivity().stage = new Stage();
		getGame2dActivity().stage.setActivity(getGame2dActivity());
		
		//call complete handler method
		getGame2dActivity().onGame2DCreationComplete();
	}
	public static Game2DActivity getGame2dActivity() {
		return game2dActivity;
	}
	private static Game2DActivity game2dActivity = null;
	
	@SuppressWarnings("unused")
	private static void nativeTouchCallback(String type,float[] touchesXY){
		Message msg = new Message();
		msg.what = Game2DHandler.MESSAGE_TYPE_NATIVE_TOUCH_CALL_BACK;
		Bundle b = new Bundle();
		b.putFloatArray(Game2DHandler.KEY_TOUCHES_XY_FLOAT_ARRAY, touchesXY);
		b.putString(Game2DHandler.KEY_TOUCH_TYPE, type);
		msg.setData(b);
		getGame2dhandler().sendMessage(msg);
	}
	
	@SuppressWarnings("unused")
	private static void nativeKeyPressCallback(String type,int keyCode){
		Message msg = new Message();
		msg.what = Game2DHandler.MESSAGE_TYPE_NATIVE_KEY_PRESS_CALL_BACK;
		Bundle b = new Bundle();
		b.putString(Game2DHandler.KEY_KEY_PRESS_TYPE, type);
		b.putInt(Game2DHandler.KEY_KEY_PRESS_CODE, keyCode);
		msg.setData(b);
		getGame2dhandler().sendMessage(msg);
	}
	
	
	public static Game2DHandler getGame2dhandler() {
		return game2dHandler;
	}
	private final static Game2DHandler game2dHandler = new Game2DHandler();
	
	
	void handleCloseEvents(){
		if (closing.dispatch(new Game2DActivityEvent(Game2DActivityEvent.CLOSING),this)) {
			close.dispatch(new Game2DActivityEvent(Game2DActivityEvent.CLOSE), this);
			
			System.exit(0);
		}
	}
	
	public static final EventListenerList<Game2DActivityEvent> closing = new EventListenerList<Game2DActivityEvent>();
	public static final EventListenerList<Game2DActivityEvent> close = new EventListenerList<Game2DActivityEvent>();
}
