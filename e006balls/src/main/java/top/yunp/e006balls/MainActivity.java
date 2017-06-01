package top.yunp.e006balls;

import android.app.Activity;
import android.os.Bundle;

import top.yunp.androidgame2d.display.GameView;
import top.yunp.androidgame2d.events.GameViewEvent;
import top.yunp.lib.java.event.EventListener;

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
			public boolean onReceive(GameViewEvent event, Object target) {
				m.move();
				return false;
			}
		});
	}
}
