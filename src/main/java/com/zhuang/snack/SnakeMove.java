package com.zhuang.snack;

import com.zhuang.constant.Constant;
import com.zhuang.enums.GameBtnEnum;
import com.zhuang.init.SnakeInit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * 蛇的移动定时器
 *
 * @module
 * @author zxd
 * @date 2022/10/21  17:59
**/
public class SnakeMove implements ActionListener {
    //游戏初始化对象
    private SnakeInit snakeInit;

    public SnakeMove(SnakeInit snakeInit){
        this.snakeInit = snakeInit;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //获取蛇的节点坐标
        SnakeNode first = snakeInit.getSnackNodeList().getFirst();
        //新增加的头部节点
        SnakeNode newFirst = null;

        switch (snakeInit.getDirection()) {
            case KeyEvent.VK_LEFT:
                //方向左
                newFirst = new SnakeNode(first.getX() - 20, first.getY(), first.getWidth(), first.getHeight());
                //判断有无撞墙
                if (newFirst.getX() < 20) {
                    snakeInit.setIsStart(false);
                }
                break;
            case KeyEvent.VK_UP:
                //方向上
                newFirst = new SnakeNode(first.getX(), first.getY() - 20, first.getWidth(), first.getHeight());
                if (newFirst.getY() < 20) {
                    snakeInit.setIsStart(false);
                }
                break;
            case KeyEvent.VK_RIGHT:
                //方向右，在头部增加一个节点，并去掉最后的节点
                newFirst = new SnakeNode(first.getX() + 20, first.getY(), first.getWidth(), first.getHeight());
                if (newFirst.getX() > 920) {
                    snakeInit.setIsStart(false);
                }
                break;
            case KeyEvent.VK_DOWN:
                //方向下
                newFirst = new SnakeNode(first.getX(), first.getY() + 20, first.getWidth(), first.getHeight());
                if (newFirst.getY() > 480) {
                    snakeInit.setIsStart(false);
                }
                break;
        }

        //判断头部是否撞到自己身体，循环判断头部的xy坐标是否和身体的xy坐标重合
        for (int i = 3; i < snakeInit.getSnackNodeList().size(); i++) {
            SnakeNode snakeNode = snakeInit.getSnackNodeList().get(i);
            if (snakeNode.getX().equals(newFirst.getX()) && snakeNode.getY().equals(newFirst.getY())) {
                //如果撞到，游戏结束
                snakeInit.setIsStart(false);
                break;
            }
        }
        //判断是否游戏结束
        if (snakeInit.getIsStart()) {
            //判断是否吃到食物，吃到了就重新生成，没吃到就删除最后一个节点
            if (newFirst.getX().equals(snakeInit.getFoodX()) && newFirst.getY().equals(snakeInit.getFoodY())) {
                snakeInit.setIsFood(true);
                //增加分数
                snakeInit.getWindowBot().addScore(Constant.FOOD_SCORE);
                //分为5个等级，初始为1级
                for (int i = 0; i < Constant.LEVEL_MAX -1; i++) {
                    //每+10分增加一个等级，每+1等级速度加快，第五级约等于原来的5倍
                    if (snakeInit.getWindowBot().getScore() == Constant.FOOD_SCORE * Constant.UP_LEVEL_NUM * (i+1)) {
                        snakeInit.getWindowBot().addLevel();
                        snakeInit.getTimer().setDelay(new Double(Constant.SNAKE_SPEED*(0.2*(4-i))).intValue());
                    }
                }
            } else {
                snakeInit.getSnackNodeList().removeLast();
            }
            //增加一个节点
            snakeInit.getSnackNodeList().addFirst(newFirst);
        } else {
            //停止移动
            snakeInit.getTimer().stop();
            snakeInit.getJPanel().setFail(true);
            snakeInit.getWindowBot().reBtn(GameBtnEnum.RESTART.getCode());
        }

        //重新绘制
        snakeInit.repaint();
    }
}
