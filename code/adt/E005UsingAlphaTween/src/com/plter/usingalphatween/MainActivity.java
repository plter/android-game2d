package com.plter.usingalphatween;

import com.plter.android.game2d.display.GameView;
import com.plter.android.game2d.display.Image;
import com.plter.android.game2d.events.TouchEvent;
import com.plter.android.game2d.tools.BitmapLoader;
import com.plter.android.game2d.tween.AlphaTween;
import com.plter.lib.java.event.EventListener;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {
	
	
	private GameView gameView;
	private Image img;
	private AlphaTween at = new AlphaTween(null, 0, 1);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		gameView = new GameView(this);
		setContentView(gameView);
		
		img = new Image(BitmapLoader.loadBitmap(this, R.drawable.ic_launcher));
		gameView.add(img);
		
		img.touchDown.add(new EventListener<TouchEvent>() {
			
			@Override
			public boolean onReceive(Object target, TouchEvent event) {
				at.setTarget(img);
				at.start();
				return false;
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
