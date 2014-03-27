package com.plter.game2d.display;

import android.app.NativeActivity;
import android.os.Bundle;

import com.plter.game2d.geom.Point;
import com.plter.game2d.lang.Array;

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
		
		//call complete handler method
		getGame2dActivity().onGame2DCreationComplete();
	}
	public static Game2DActivity getGame2dActivity() {
		return game2dActivity;
	}
	private static Game2DActivity game2dActivity = null;
	
	@SuppressWarnings("unused")
	private static void nativeTouchCallback(String type,float[] touchesXY){
		Array<Point> touches = new Array<Point>();
		for (int i = 0; i < touchesXY.length; i+=2) {
			touches.push(new Point(touchesXY[i], touchesXY[i+1]));
		}
		
		getGame2dActivity().getStage().onTouchHandler(type, touches);
	}
	
	
	public static Game2DHandler getGame2dhandler() {
		return game2dHandler;
	}
	private final static Game2DHandler game2dHandler = new Game2DHandler();
}
