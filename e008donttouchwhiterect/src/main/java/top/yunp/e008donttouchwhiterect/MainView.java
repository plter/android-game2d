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

    /**
     * 方块被选中时的事件侦听器
     */
    private RectLine.OnRectSelected rectSelectedHandler = new RectLine.OnRectSelected() {
        @Override
        public void onSelect(Rect rect, RectLine target) {

            //如果点中的是黑色方块，则在最上边添加一行，游戏继续，否则可提示用户游戏结束
            if (rect.isBlack()) {
                //只有在所有动画效果都结束之后才能执行新的动画效果
                if (isAllTweenEnded()) {
                    //在最上边添加一行
                    addNewLineAtIndex(-1);
                    //将所有方块向下移一行
                    moveAllLinesDown();
                }
            } else {
                Toast.makeText(getContext(), "点错了", Toast.LENGTH_SHORT).show();
            }
        }
    };

    /**
     * 用于存放所有方块的数组
     */
    private List<RectLine> rectLines = new ArrayList<>();

    /**
     * 方块行向下移动动画执行结束时的事件处理器
     */
    private RectLine.OnLineMoveDownTweenEnd lineMoveTweenEndHandler = new RectLine.OnLineMoveDownTweenEnd() {
        @Override
        public void onEnd(RectLine target) {
            //如果当前结束动画的方块行的索引是4，则意味着该行已经超出了屏幕下边，则将该行移除
            if (target.getPositionIndex() >= 4) {
                rectLines.remove(target);
                remove(target);
            }
        }
    };

    public MainView(Context context) {
        super(context);

        //设置游戏刷新的帧频务50
        setFps(50);
        //设置游戏背景颜色为黑色
        setGameViewBackground(0xff000000);

        //呈现对话框用于启动游戏
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
        //初始化一些属性，这些属性将是游戏中元素的重要数据，如根据屏幕动态获取卡片的宽高
        initProperties();

        //在屏幕上添加4X4方块矩阵
        addRectLines();
    }

    private void addRectLines() {
        for (int i = 0; i < 4; i++) {
            addNewLineAtIndex(i);
        }
    }

    /**
     * 在特定的索引位置添加一行方块，每行方块数量是4个
     * @param index
     */
    private void addNewLineAtIndex(int index) {
        RectLine line = new RectLine();
        line.setOnRectSelected(rectSelectedHandler);
        line.setPositionIndex(index);

        //根据行的索引位置确定它的坐标位置
        line.setPositionYByIndex();
        line.setOnLineMoveDownTweenEnd(lineMoveTweenEndHandler);

        //将行添加到GameView视图中
        add(line);
        //在数组中记录已被添加到视图中的方块行对象
        rectLines.add(line);
    }

    /**
     * 将所有行往下移动一行
     */
    private void moveAllLinesDown() {
        for (RectLine rl : rectLines) {
            //将位置索引自加1
            rl.setPositionIndex(rl.getPositionIndex() + 1);
            //根据位置索引将行移动到特定位置
            rl.moveToTargetPositionByIndex();
        }
    }

    /**
     * 检测是否所有的方块行都已停止了动画
     * @return
     */
    private boolean isAllTweenEnded() {
        for (RectLine l : rectLines) {
            if (l.isTweenRunning()) {
                return false;
            }
        }
        return true;
    }

    /**
     * 初始化属性
     */
    private void initProperties() {
        Config.setScreenWidth(getWidth());
        Config.setScreenHeight(getHeight());
        Config.setCardWidth(getWidth() / 4);
        Config.setCardHeight(getHeight() / 4);
    }
}
