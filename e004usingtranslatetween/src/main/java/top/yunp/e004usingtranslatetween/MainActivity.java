package top.yunp.e004usingtranslatetween;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Path.Direction;
import android.graphics.RectF;
import android.os.Bundle;

import top.yunp.androidgame2d.display.GameView;
import top.yunp.androidgame2d.display.Shape;
import top.yunp.androidgame2d.events.TouchEvent;
import top.yunp.androidgame2d.tween.TranslateTween;
import top.yunp.androidgame2d.tween.Tween;
import top.yunp.lib.java.event.EventListener;

public class MainActivity extends Activity {

    private GameView gameView;
    private Shape shape;
    private Tween tween;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tween = new TranslateTween(null, 0, 0, 100, 100);

        gameView = new GameView(this);
        setContentView(gameView);

        shape = new Shape();
        shape.getPaint().setColor(Color.RED);
        shape.getPath().addRect(new RectF(0, 0, 100, 100), Direction.CW);

        shape.touchDown.add(new EventListener<TouchEvent>() {

            @Override
            public boolean onReceive(TouchEvent event, Object target) {
                tween.setTarget(shape);
                tween.start();
                return false;
            }
        });

        gameView.add(shape);
    }
}
