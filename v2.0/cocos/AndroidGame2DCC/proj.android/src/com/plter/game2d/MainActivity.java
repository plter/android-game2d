package com.plter.game2d;

import android.os.Bundle;

import com.plter.game2d.display.Game2DActivity;
import com.plter.game2d.display.Label;
import com.plter.game2d.display.Alert;
import com.plter.game2d.events.EventListener;
import com.plter.game2d.events.TouchEvent;
import com.plter.game2d.geom.Size;

public class MainActivity extends Game2DActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	
	@Override
	protected void onGame2DCreationComplete() {
		
		Size s = getStage().getVisibleSize();
		
		Label l = new Label("你好");
		l.setPosition(s.getWidth()/2, s.getHeight()/2);
		
		getStage().addChild(l);
		
		l.onTouch.add(new EventListener<TouchEvent>(TouchEvent.TOUCH_BEGAN) {
			
			@Override
			public boolean onReceive(TouchEvent event, Object target) {
				Alert.showBox("Haha");
				return false;
			}
		});
	}
}
