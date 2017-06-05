package top.yunp.e008donttouchwhiterect;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import top.yunp.androidgame2d.display.GameView;

/**
 * Created by plter on 6/5/17.
 */

public class MainView extends GameView {
    public MainView(Context context) {
        super(context);

        setGameViewBackground(0xffcccccc);
        shouldStartGame();
    }

    private void shouldStartGame() {
        new AlertDialog.Builder(getContext())
                .setTitle("欢迎使用")
                .setMessage("点击『开始』按钮开始游戏")
                .setPositiveButton("开始", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startGame();
                    }
                })
                .setNegativeButton("关闭", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getActivity().finish();
                    }
                })
                .show();
    }

    public MainActivity getActivity() {
        return (MainActivity) getContext();
    }

    private void startGame() {
        initProperties();

        RectLine rl = new RectLine();
        add(rl);
    }

    private void initProperties() {
        Config.setScreenWidth(getWidth());
        Config.setScreenHeight(getHeight());
        Config.setCardWidth(getWidth() / 4);
        Config.setCardHeight(getHeight() / 4);
    }
}
