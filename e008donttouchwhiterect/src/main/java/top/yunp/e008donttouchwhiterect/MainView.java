package top.yunp.e008donttouchwhiterect;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import top.yunp.androidgame2d.display.GameView;

/**
 * Created by plter on 6/5/17.
 */

public class MainView extends GameView {
    private RectLine.OnRectSelected rectSelectedHandler = new RectLine.OnRectSelected() {
        @Override
        public void onSelect(Rect rect, RectLine target) {

            if (rect.isBlack()) {
                if (isAllTweenEnded()) {
                    addNewLineAtIndex(-1);
                    moveAllLinesDown();
                }
            } else {
                Toast.makeText(getContext(), "点错了", Toast.LENGTH_SHORT).show();
            }
        }
    };

    private List<RectLine> rectLines = new ArrayList<>();
    private RectLine.OnLineMoveDownTweenEnd lineMoveTweenEndHandler = new RectLine.OnLineMoveDownTweenEnd() {
        @Override
        public void onEnd(RectLine target) {
            if (target.getPositionIndex() >= 4) {
                rectLines.remove(target);
                remove(target);
            }
        }
    };

    public MainView(Context context) {
        super(context);

        setFps(50);
        setGameViewBackground(0xff000000);
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

        addRectLines();
    }

    private void addRectLines() {
        for (int i = 0; i < 4; i++) {
            addNewLineAtIndex(i);
        }
    }

    private void addNewLineAtIndex(int index) {
        RectLine line = new RectLine();
        line.setOnRectSelected(rectSelectedHandler);
        line.setPositionIndex(index);
        line.setPositionYByIndex();
        line.setOnLineMoveDownTweenEnd(lineMoveTweenEndHandler);
        add(line);
        rectLines.add(line);
    }

    private void moveAllLinesDown() {
        for (RectLine rl : rectLines) {
            rl.setPositionIndex(rl.getPositionIndex() + 1);
            rl.moveToTargetPositionByIndex();
        }
    }

    private boolean isAllTweenEnded() {
        for (RectLine l : rectLines) {
            if (l.isTweenRunning()) {
                return false;
            }
        }
        return true;
    }

    private void initProperties() {
        Config.setScreenWidth(getWidth());
        Config.setScreenHeight(getHeight());
        Config.setCardWidth(getWidth() / 4);
        Config.setCardHeight(getHeight() / 4);
    }
}
