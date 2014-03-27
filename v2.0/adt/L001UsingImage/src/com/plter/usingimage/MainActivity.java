package com.plter.usingimage;

import android.os.Bundle;

import com.plter.game2d.display.Game2DActivity;
import com.plter.game2d.display.Image;
import com.plter.game2d.geom.Size;

public class MainActivity extends Game2DActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	protected void onGame2DCreationComplete() {
		
		Size size = getStage().getVisibleSize();
		
		Image img = new Image("image.jpg");
		img.setPosition(size.getWidth()/2, size.getHeight()/2);
		getStage().addChild(img);
		
	}

}
