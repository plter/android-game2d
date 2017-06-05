package top.yunp.e008donttouchwhiterect;

/**
 * Created by plter on 6/5/17.
 */

public class Config {

    private static int screenWidth, screenHeight, cardWidth, cardHeight;

    public static int getScreenWidth() {
        return screenWidth;
    }

    public static void setScreenWidth(int screenWidth) {
        Config.screenWidth = screenWidth;
    }

    public static int getScreenHeight() {
        return screenHeight;
    }

    public static void setScreenHeight(int screenHeight) {
        Config.screenHeight = screenHeight;
    }

    public static int getCardWidth() {
        return cardWidth;
    }

    public static void setCardWidth(int cardWidth) {
        Config.cardWidth = cardWidth;
    }

    public static int getCardHeight() {
        return cardHeight;
    }

    public static void setCardHeight(int cardHeight) {
        Config.cardHeight = cardHeight;
    }
}
