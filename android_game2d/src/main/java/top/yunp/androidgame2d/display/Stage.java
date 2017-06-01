package top.yunp.androidgame2d.display;

public class Stage extends Container {

	Stage() {
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
