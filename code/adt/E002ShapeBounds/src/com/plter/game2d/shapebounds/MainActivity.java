package com.plter.game2d.shapebounds;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint.Style;
import android.graphics.Path.Direction;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.Menu;

import com.plter.android.game2d.display.GameView;
import com.plter.android.game2d.display.Image;
import com.plter.android.game2d.display.Shape;
import com.plter.android.game2d.display.TextLine;
import com.plter.android.game2d.events.GameViewEvent;
import com.plter.android.game2d.events.TouchEvent;
import com.plter.android.game2d.tools.BitmapLoader;
import com.plter.lib.java.event.EventListener;

public class MainActivity extends Activity {
	
	
	private GameView gameView;
	private Shape shape;
	private Image img;
	private Shape bounds;
	private TextLine tl;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		gameView = new GameView(this);
		
		shape = new Shape();
		shape.getPath().addRect(new RectF(0, 0, 50, 50), Direction.CW);
		shape.x=75;
		shape.y=75;
		shape.regX=25;
		shape.regY=25;
		shape.scaleX=2;
		
		img = new Image(BitmapLoader.loadBitmap(this, R.drawable.ic_launcher));
		img.x=200;
		img.y=100;
		img.regX=50;
		
		tl = new TextLine("Hello Game2d");
		tl.x=100;
		tl.y=250;
		tl.regX=50;
		tl.regY=20;
		
		bounds= new Shape();
		bounds.getPaint().setStyle(Style.STROKE);
		bounds.getPaint().setColor(Color.RED);
		
		shape.touch.add(new EventListener<TouchEvent>() {
			
			@Override
			public boolean onReceive(Object arg0, TouchEvent arg1) {
				System.out.println(arg1.getType());
				return false;
			}
		});
		
		gameView.enterFrame.add(new EventListener<GameViewEvent>() {
			
			@Override
			public boolean onReceive(Object arg0, GameViewEvent arg1) {
				shape.rotation+=1;
				img.rotation-=2;
				tl.rotation+=3;
				
				bounds.getPath().reset();
				bounds.getPath().addRect(shape.getBounds(), Direction.CW);
				bounds.getPath().addRect(img.getBounds(), Direction.CW);
				bounds.getPath().addRect(tl.getBounds(), Direction.CW);
				
				return false;
			}
		});
		
		
		gameView.add(bounds);
		gameView.add(shape);
		gameView.add(img);
		gameView.add(tl);
		
		setContentView(gameView);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
