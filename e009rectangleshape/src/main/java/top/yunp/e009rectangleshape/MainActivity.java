package top.yunp.e009rectangleshape;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import top.yunp.androidgame2d.display.GameView;
import top.yunp.androidgame2d.display.Rectangle;
import top.yunp.androidgame2d.display.RectangleStroke;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GameView gv = new GameView(this);
        setContentView(gv);

        Rectangle r = new Rectangle(100, 100, 0xffff0000);
        gv.add(r);
        r.setX(100);
        r.setY(100);

        RectangleStroke rs = new RectangleStroke(100, 100, 0xff0000ff);
        gv.add(rs);
        rs.getPaint().setStrokeWidth(10);
        rs.setX(100);
        rs.setY(100);
    }
}
