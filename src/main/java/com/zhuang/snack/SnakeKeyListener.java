package com.zhuang.snack;

import com.zhuang.enums.DirectionEnum;
import com.zhuang.init.SnakeInit;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * 键盘事件
 *
 * @module
 * @author zxd
 * @date 2022/10/21  13:03
**/
public class SnakeKeyListener implements KeyListener {
    @Override
    public void keyPressed(KeyEvent e) {
        SnakeInit source = (SnakeInit) e.getSource();
        //节点的第一和第二个
        SnakeNode first = source.getSnackNodeList().getFirst();
        SnakeNode two = source.getSnackNodeList().get(1);
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                //如果是按方向键左，并且头部和第二个节点不能在同一个水平线上，防止两个键按太快导致可以倒着走
                if (source.getDirection() != DirectionEnum.RIGHT.getCode() && first.getY() != two.getY()) {
                    source.setDirection(DirectionEnum.LEFT.getCode());
                }
                break;
            case KeyEvent.VK_UP:
                //如果是按方向键上，并且头部和第二个节点不能在同一个垂直线上
                if (source.getDirection() != DirectionEnum.DOWN.getCode() && first.getX() != two.getX()) {
                    source.setDirection(DirectionEnum.UP.getCode());
                }
                break;
            case KeyEvent.VK_RIGHT:
                //如果是按方向键右，并且头部和第二个节点不能在同一个水平线上
                if (source.getDirection() != DirectionEnum.LEFT.getCode() && first.getY() != two.getY()) {
                    source.setDirection(DirectionEnum.RIGHT.getCode());
                }
                break;
            case KeyEvent.VK_DOWN:
                //如果是按方向键下，并且头部和第二个节点不能在同一个垂直线上
                if (source.getDirection() != DirectionEnum.UP.getCode() && first.getX() != two.getX()) {
                    source.setDirection(DirectionEnum.DOWN.getCode());
                }
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
