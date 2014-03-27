package com.plter.game2d.display;

import android.app.AlertDialog;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

class Game2DHandler extends Handler {

	@Override
	public void handleMessage(Message msg) {
		switch (msg.what) {
		case Game2DHandler.MESSAGE_TYPE_SHOW_ALERT_DIALOG:
			new AlertDialog.Builder(Game2DActivity.getGame2dActivity()).setMessage((String)msg.obj).setTitle("Game2D").setPositiveButton("OK", null).show();
			break;
		case Game2DHandler.MESSAGE_TYPE_SHOW_TOAST:
			Toast.makeText(Game2DActivity.getGame2dActivity(), (String)msg.obj, Toast.LENGTH_SHORT).show();
			break;
		}
	}
	
	public static final int MESSAGE_TYPE_SHOW_ALERT_DIALOG = 1;
	public static final int MESSAGE_TYPE_SHOW_TOAST = 2;
}
