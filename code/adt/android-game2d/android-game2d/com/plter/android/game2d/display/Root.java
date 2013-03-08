package com.plter.android.game2d.display;

public class Root extends Container {

	Root() {
	}
	
	@Override
	public GameView getGameView() {
		return gameView;
	}
	
	void internal_setGameView(GameView gameView){
		this.gameView=gameView;
	}
	
	private GameView gameView = null;
}
