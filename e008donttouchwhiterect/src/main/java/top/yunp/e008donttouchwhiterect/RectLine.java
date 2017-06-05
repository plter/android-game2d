package top.yunp.e008donttouchwhiterect;

import java.util.ArrayList;
import java.util.List;

import top.yunp.androidgame2d.display.Container;

/**
 * Created by plter on 6/5/17.
 */

public class RectLine extends Container {

    private List<Rect> rects = new ArrayList<>();

    public RectLine() {

        Rect r;

        for (int i = 0; i < 4; i++) {
            r = new Rect(false);
            r.setX(Config.getCardWidth() * i);
            add(r);
            rects.add(r);
        }

        rects.get((int) (Math.random() * rects.size())).setBlack(true);
    }
}
