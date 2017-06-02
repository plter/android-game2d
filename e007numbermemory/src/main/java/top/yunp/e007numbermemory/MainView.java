package top.yunp.e007numbermemory;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.PointF;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import top.yunp.androidgame2d.display.Display;
import top.yunp.androidgame2d.display.GameView;
import top.yunp.androidgame2d.events.TouchEvent;
import top.yunp.lib.java.event.EventListener;

/**
 * Created by plter on 6/2/17.
 */

public class MainView extends GameView {

    private List<PointF> points = new ArrayList<>();
    private EventListener<TouchEvent, Display> ncTouchDownHandler = new EventListener<TouchEvent, Display>() {
        @Override
        public boolean onReceive(TouchEvent event, Display display) {
            if (display instanceof NumberCard) {
                if (((NumberCard) display).getNumber() == currentNum) {
                    remove(display);
                    cards.remove(display);

                    //将所有卡片翻转到反面
                    if (currentNum == 1) {
                        for (NumberCard c : cards) {
                            c.turnToVerso();
                        }
                    }

                    currentNum++;

                    //当卡片数组中数量为0时，则意味着你完成了游戏
                    if (cards.size() <= 0) {
                        new AlertDialog.Builder(getContext())
                                .setTitle("提示")
                                .setMessage("你赢了")
                                .setPositiveButton("OK", null)
                                .show();
                    }
                } else {
                    Toast.makeText(getContext(), "点错了", Toast.LENGTH_SHORT).show();
                }
            }
            return false;
        }
    };

    private int currentNum = 1;
    private List<NumberCard> cards = new ArrayList<>();

    public MainView(Context context) {
        super(context);

        startGame();
    }

    private void startGame() {

        currentNum = 1;
        addCards();
    }

    /**
     * 向视图中添加卡片
     */
    private void addCards() {
        float globalOffsetX = Config.CARD_WIDTH / 2;
        float globalOffsetY = Config.CARD_HEIGHT / 2;

        //重置坐标点数组
        points.clear();
        //生成可用坐标点
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 8; j++) {
                points.add(new PointF(i * Config.CARD_WIDTH + globalOffsetX, j * Config.CARD_HEIGHT + globalOffsetY));
            }
        }

        PointF p;
        NumberCard nc;
        //从可用坐标点中取得10个坐标点，用于设置卡片的位置
        for (int i = 1; i <= 10; i++) {
            p = points.remove((int) (points.size() * Math.random()));
            nc = new NumberCard(i);
            nc.setX(p.x).setY(p.y);
            add(nc);
            cards.add(nc);
            nc.touchDown.add(ncTouchDownHandler);
        }
    }

}
