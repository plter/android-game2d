package com.plter.usinglabel;

import com.plter.game2d.display.Game2DActivity;
import com.plter.game2d.display.Label;
import com.plter.game2d.geom.Size;

public class MainActivity extends Game2DActivity {

	@Override
	protected void onGame2DCreationComplete() {
		
		Label l = new Label("Hello Game2D");
		Size s = getStage().getVisibleSize();
		
		l.setPosition(s.getWidth()/2, s.getHeight()/2);
		getStage().addChild(l);
	}
}
