package top.yunp.e007numbermemory;

import android.graphics.Color;
import android.graphics.Path;

import top.yunp.androidgame2d.display.Shape;
import top.yunp.androidgame2d.display.Sprite;
import top.yunp.androidgame2d.display.TextLine;
import top.yunp.androidgame2d.events.TweenEvent;
import top.yunp.androidgame2d.tween.ScaleTween;
import top.yunp.androidgame2d.tween.Tween;
import top.yunp.lib.java.event.EventListener;

/**
 * Created by plter on 6/2/17.
 */

public class NumberCard extends Sprite {

    private int number = 0;
    private Sprite recto;//声明卡片的正面
    private Shape verso;//声明卡片的反面
    private ScaleTween scaleX1To0 = new ScaleTween(null, 1, 1, 0, 1);
    private ScaleTween scaleX0To1 = new ScaleTween(null, 0, 1, 1, 1);
    private boolean tweenRunning = false;

    public NumberCard(int number) {
        this.number = number;

        buildUI();
        buildTweens();
    }


    private void buildUI() {

        //卡片的半宽
        int halfWidth = Config.getCardWidthInPx() / 2;
        //卡片的半高
        int halfHeight = Config.getCardHeightInPx() / 2;
        int margin = 5;

        //build recto

        //创建卡片正面图形
        recto = new Sprite();
        //创建卡片正面图形的背景
        Shape rectoBg = new Shape();
        //为正面背景设置为红色
        rectoBg.getPaint().setColor(Color.RED);
        //为正面背景添加一个矩形框
        rectoBg.getPath().addRect(-halfWidth + margin, -halfHeight + margin, halfWidth - margin, halfHeight - margin, Path.Direction.CW);
        //该背景添加到正面图形中
        recto.add(rectoBg);
        //将卡片正面图形添加到该卡片中
        add(recto);

        //创建卡片正面的数字文本对象
        TextLine tl = new TextLine(String.valueOf(getNumber()));
        //设置文字的大小
        tl.setSize(Config.getTextSize());
        //将文字添加到正面图形中
        recto.add(tl);
        //计算文字的高度
        int lineHeight = tl.getLineHeight();
        //将文字相对于卡片垂直居中
        tl.setY(lineHeight / 2);
        //将文字相对于卡片水平居中
        tl.setX(-tl.getBounds().width() / 2);
        //设计文字的颜色为白色
        tl.getPaint().setColor(Color.WHITE);

        //创建卡片反面
        verso = new Shape();
        //设置反面的颜色为蓝色
        verso.getPaint().setColor(Color.BLUE);
        //为反面添加一个矩形框
        verso.getPath().addRect(-halfWidth + margin, -halfHeight + margin, halfWidth - margin, halfHeight - margin, Path.Direction.CW);
        //将反面添加到该卡片中
        add(verso);

        //呈现正面
        showRecto();
    }


    private void buildTweens() {
        scaleX0To1.setFrames(5);
        scaleX1To0.setFrames(5);

        scaleX1To0.tweenEnd.add(new EventListener<TweenEvent, Tween>() {
            @Override
            public boolean onReceive(TweenEvent event, Tween tween) {
                if (getRecto().isVisible()) {
                    getVerso().setScaleX(0);
                    showVerso();
                    scaleX0To1.setTarget(getVerso()).start();
                } else {
                    getRecto().setScaleX(0);
                    showRecto();
                    scaleX0To1.setTarget(getRecto()).start();
                }
                return false;
            }
        });
        scaleX0To1.tweenEnd.add(new EventListener<TweenEvent, Tween>() {
            @Override
            public boolean onReceive(TweenEvent event, Tween tween) {
                tweenRunning = false;
                return false;
            }
        });
    }

    /**
     * 呈现卡片的正面
     */
    public void showRecto() {
        recto.setVisible(true);
        verso.setVisible(false);
    }

    /**
     * 呈现卡片的反面
     */
    public void showVerso() {
        recto.setVisible(false);
        verso.setVisible(true);
    }

    /**
     * 将卡片翻转到正面
     */
    public void turnToRecto() {
        if (!tweenRunning && getVerso().isVisible()) {
            scaleX1To0.setTarget(getVerso()).start();
            tweenRunning = true;
        }
    }

    /**
     * 将卡片翻转到反面
     */
    public void turnToVerso() {
        if (!tweenRunning && getRecto().isVisible()) {
            scaleX1To0.setTarget(getRecto()).start();
            tweenRunning = true;
        }
    }

    /**
     * 获取卡片的正面
     *
     * @return
     */
    public Sprite getRecto() {
        return recto;
    }

    /**
     * 获取卡片的反面
     *
     * @return
     */
    public Shape getVerso() {
        return verso;
    }

    /**
     * 获取该卡片中的数字
     *
     * @return
     */
    public int getNumber() {
        return number;
    }
}
