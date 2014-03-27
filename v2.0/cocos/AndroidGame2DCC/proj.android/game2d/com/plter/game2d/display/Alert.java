package com.plter.game2d.display;


public class Alert {

	public static void showBox(String msg){
		android.os.Message m = new android.os.Message();
		m.obj = msg;
		m.what = Game2DHandler.MESSAGE_TYPE_SHOW_ALERT_DIALOG;
		Game2DActivity.getGame2dhandler().sendMessage(m);
	}
	
	public static void showToast(String msg){
		android.os.Message m = new android.os.Message();
		m.obj = msg;
		m.what = Game2DHandler.MESSAGE_TYPE_SHOW_TOAST;
		Game2DActivity.getGame2dhandler().sendMessage(m);
	}
}
