package com.plter.balls;

import com.plter.android.game2d.display.GameView;
import com.plter.android.game2d.events.GameViewEvent;
import com.plter.lib.java.event.EventListener;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		GameView gv = new GameView(this);
		gv.setFps(60);
		final Main m = new Main();
		setContentView(gv);
		gv.add(m);
		
		gv.enterFrame.add(new EventListener<GameViewEvent>() {
			
			@Override
			public boolean onReceive(Object target, GameViewEvent event) {
				m.move();
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
