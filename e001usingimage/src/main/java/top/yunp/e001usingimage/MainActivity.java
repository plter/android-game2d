package top.yunp.e001usingimage;

import android.app.Activity;
import android.os.Bundle;

import top.yunp.androidgame2d.display.GameView;
import top.yunp.androidgame2d.display.Image;
import top.yunp.androidgame2d.tools.BitmapLoader;

public class MainActivity extends Activity {


    private GameView gameView;
    private Image i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameView = new GameView(this);
        setContentView(gameView);

        i = new Image(BitmapLoader.loadBitmap(this, R.mipmap.ic_launcher));
        i.setX(100);
        i.setY(100);
        gameView.add(i);
    }
}
