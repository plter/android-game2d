package top.yunp.e002shapebounds;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint.Style;
import android.graphics.Path.Direction;
import android.graphics.RectF;
import android.os.Bundle;

import top.yunp.androidgame2d.display.Display;
import top.yunp.androidgame2d.display.GameView;
import top.yunp.androidgame2d.display.Image;
import top.yunp.androidgame2d.display.Shape;
import top.yunp.androidgame2d.display.TextLine;
import top.yunp.androidgame2d.events.GameViewEvent;
import top.yunp.androidgame2d.events.TouchEvent;
import top.yunp.androidgame2d.tools.BitmapLoader;
import top.yunp.lib.java.event.EventListener;

public class MainActivity extends Activity {


    private GameView gameView;
    private Shape shape;
    private Image img;
    private Shape bounds;
    private TextLine tl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        gameView = new GameView(this);
//		gameView.setFps(60);

        shape = new Shape();
        shape.getPath().addRect(new RectF(0, 0, 50, 50), Direction.CW);
        shape.setX(75);
        shape.setY(75);
        shape.setRegX(25);
        shape.setRegY(25);
        shape.setScaleX(2);

        img = new Image(BitmapLoader.loadBitmap(this, R.mipmap.ic_launcher));
        img.setX(200);
        img.setY(100);
        img.setRegX(50);

        tl = new TextLine("Hello Game2d");
        tl.setX(100);
        tl.setY(250);
        tl.setRegX(50);
        tl.setRegY(20);

        bounds = new Shape();
        bounds.getPaint().setStyle(Style.STROKE);
        bounds.getPaint().setColor(Color.RED);

        shape.touch.add(new EventListener<TouchEvent, Display>(null) {

            @Override
            public boolean onReceive(TouchEvent event, Display target) {
                System.out.println(event.getName());
                return false;
            }
        });

        gameView.enterFrame.add(new EventListener<GameViewEvent, GameView>() {

            @Override
            public boolean onReceive(GameViewEvent event, GameView target) {
                shape.setRotation(shape.getRotation() + 1);
                img.setRotation(img.getRotation() - 2);
                tl.setRotation(tl.getRotation() + 3);

                bounds.getPath().reset();
                bounds.getPath().addRect(shape.getBounds(), Direction.CW);
                bounds.getPath().close();
                bounds.getPath().addRect(img.getBounds(), Direction.CW);
                bounds.getPath().close();
                bounds.getPath().addRect(tl.getBounds(), Direction.CW);
                bounds.getPath().close();

                return false;
            }
        });


        gameView.add(bounds);
        gameView.add(shape);
        gameView.add(img);
        gameView.add(tl);

        setContentView(gameView);
    }
}
