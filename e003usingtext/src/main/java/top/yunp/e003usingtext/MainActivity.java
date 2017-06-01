package top.yunp.e003usingtext;

import android.app.Activity;
import android.os.Bundle;

import top.yunp.androidgame2d.display.GameView;
import top.yunp.androidgame2d.display.TextLine;

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
		t.setY(t.getLineHeight());
		gameView.add(t);
	}
}
