package com.plter.game2d.usingimage;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.plter.android.game2d.display.GameView;
import com.plter.android.game2d.display.Image;
import com.plter.android.game2d.tools.BitmapLoader;

public class MainActivity extends Activity {
	
	
	private GameView gameView;
	private Image i;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		gameView=new GameView(this);
		setContentView(gameView);
		
		i = new Image(BitmapLoader.loadBitmap(this, R.drawable.ic_launcher));
		i.x=100;
		i.y=100;
		gameView.add(i);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
