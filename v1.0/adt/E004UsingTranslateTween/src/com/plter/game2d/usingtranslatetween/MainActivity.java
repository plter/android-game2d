package com.plter.game2d.usingtranslatetween;

import android.app.Activity;
import android.graphics.Path.Direction;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.Menu;

import com.plter.android.game2d.display.GameView;
import com.plter.android.game2d.display.Shape;
import com.plter.android.game2d.events.TouchEvent;
import com.plter.android.game2d.tween.TranslateTween;
import com.plter.lib.java.event.EventListener;

public class MainActivity extends Activity {
	
	private GameView gameView;
	private Shape shape;
	private TranslateTween tween;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		tween = new TranslateTween(null,0,0,100,100);
		
		gameView = new GameView(this);
		setContentView(gameView);
		
		shape = new Shape();
		shape.getPath().addRect(new RectF(0, 0, 100, 100), Direction.CW);
		
		shape.touchDown.add(new EventListener<TouchEvent>() {
			
			@Override
			public boolean onReceive(Object target, TouchEvent event) {
				tween.setTarget(shape);
				tween.start();
				return false;
			}
		});
		
		gameView.add(shape);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
