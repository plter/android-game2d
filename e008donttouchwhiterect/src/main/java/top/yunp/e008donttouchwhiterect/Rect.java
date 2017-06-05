package top.yunp.e008donttouchwhiterect;

import top.yunp.androidgame2d.display.Rectangle;

/**
 * Created by plter on 6/5/17.
 * <p>
 * 构造Rect方块对象，在构造该对象之前请保证已调用了Config.setCardWidth和Config.setCardHeight对相关属性进行了初始化
 */
public class Rect extends Rectangle {

    private boolean black = false;

    public Rect(boolean isBlack) {
        super(Config.getCardWidth(), Config.getCardHeight(), isBlack ? 0xff000000 : 0xffffffff);

        black = isBlack;
    }

    public boolean isBlack() {
        return black;
    }

    public void setBlack(boolean black) {
        this.black = black;
        setColor(black ? 0xff000000 : 0xffffffff);
    }
}
