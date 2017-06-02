package top.yunp.e007numbermemory;

import android.content.Context;

import top.yunp.androidgame2d.display.GameView;
import top.yunp.androidgame2d.events.TouchEvent;
import top.yunp.lib.java.event.EventListener;

/**
 * Created by plter on 6/2/17.
 */

public class MainView extends GameView {
    public MainView(Context context) {
        super(context);

        NumberCard nc = new NumberCard(8);
        nc.setX(150);
        nc.setY(50);
        add(nc);

        nc.touchDown.add(new EventListener<TouchEvent>() {
            @Override
            public boolean onReceive(TouchEvent event, Object target) {
                NumberCard c = (NumberCard) target;

                if (c.getRecto().isVisible()) {
                    c.showVerso();
                } else {
                    c.showRecto();
                }
                return false;
            }
        });
    }
}
