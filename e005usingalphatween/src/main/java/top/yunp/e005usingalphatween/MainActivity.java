package top.yunp.e005usingalphatween;

import android.app.Activity;
import android.os.Bundle;

import top.yunp.androidgame2d.display.Display;
import top.yunp.androidgame2d.display.GameView;
import top.yunp.androidgame2d.display.Image;
import top.yunp.androidgame2d.events.TouchEvent;
import top.yunp.androidgame2d.tools.BitmapLoader;
import top.yunp.androidgame2d.tween.AlphaTween;
import top.yunp.lib.java.event.EventListener;

public class MainActivity extends Activity {


    private GameView gameView;
    private Image img;
    private AlphaTween at = new AlphaTween(null, 0, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gameView = new GameView(this);
        setContentView(gameView);

        img = new Image(BitmapLoader.loadBitmap(this, R.mipmap.ic_launcher));
        gameView.add(img);

        img.touchDown.add(new EventListener<TouchEvent,Display>() {

            @Override
            public boolean onReceive(TouchEvent event, Display target) {
                at.setTarget(img);
                at.start();
                return false;
            }
        });
    }
}
