package top.yunp.e007numbermemory;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by plter on 6/2/17.
 */

public class Config {

    public static void initConfig(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        _cardWidthInPx = (int) (displayMetrics.density * CARD_WIDTH_IN_DP);
        _cardHeightInPx = (int) (displayMetrics.density * CARD_HEIGHT_IN_DP);
        _textSize = (int) (0.8 * _cardHeightInPx);
    }

    private static final int CARD_WIDTH_IN_DP = 60;
    private static final int CARD_HEIGHT_IN_DP = 60;

    public static int getCardWidthInPx() {
        return _cardWidthInPx;
    }

    public static int getCardHeightInPx() {
        return _cardHeightInPx;
    }

    public static int getTextSize() {
        return _textSize;
    }

    private static int _cardWidthInPx, _cardHeightInPx, _textSize;
}
