package com.plter.usingtext;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.plter.android.game2d.display.GameView;
import com.plter.android.game2d.display.TextLine;

public class MainActivity extends Activity {
	
	
	private GameView gameView;
	private TextLine t;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		gameView = new GameView(this);
		setContentView(gameView);
		
		t = new TextLine("Hello Android Game2d");
		t.setSize(24);
		t.y=t.getLineHeight();
		gameView.add(t);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
