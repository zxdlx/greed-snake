package com.zhuang.component;

import com.zhuang.constant.Constant;
import com.zhuang.init.SnakeInit;
import com.zhuang.snack.SnakeNode;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GamePanel extends JPanel {
    //游戏初始化对象
    private SnakeInit snakeInit;
    //判断是否失败
    private boolean isFail;

    public GamePanel(SnakeInit snakeInit) {
        this.isFail = false;
        setBounds(0,0,Constant.GAME_WIDTH,Constant.GAME_HEIGHT);
        this.snakeInit= snakeInit;
    }

    @Override
    public void paint(Graphics g) {
        //初始化背景
        super.paint(g);
        g.setColor(Color.black);
        //横线，每个格子20px
//                for (int i = 40;i<540;i+=20){
//                    g.drawLine(20,i,938,i);
//                }
//                //竖线，每个格子20px
//                for (int i = 20;i<950;i+=20){
//                    g.drawLine(i,40,i,518);
//                }
        //绘制背景
        g.fillRect(0,0, Constant.GAME_WIDTH,Constant.GAME_HEIGHT);
        g.setColor(new Color(0xA4E7AD69,true));
        g.drawRect(20,20,920,480);
        //循环把蛇的身体节点都绘画进图里
        for (SnakeNode snakeNode : snakeInit.getSnackNodeList()) {
            if (snakeNode == snakeInit.getSnackNodeList().getFirst()) {
                //绘制蛇头
                g.setColor(new Color(0xA461B3C4,true));
                g.fillRect(snakeNode.getX(), snakeNode.getY(), snakeNode.getWidth(), snakeNode.getHeight());
            }else {
                //绘制蛇身
                g.setColor(new Color(0xA4F1F1F1,true));
                g.fillRect(snakeNode.getX(), snakeNode.getY(), snakeNode.getWidth(), snakeNode.getHeight());
            }
        }
        //绘制食物，随机生成
        if (snakeInit.getIsFood()) {
            randomFood();
        }
        g.setColor(new Color(0xA429B116, true));
        g.fillOval(snakeInit.getFoodX(), snakeInit.getFoodY(), 19, 19);

        //失败总结
        if (isFail) {
            g.setColor(new Color(0xA429B116, true));
            g.setFont(new Font("宋体", Font.PLAIN, 40));
            g.drawString("游戏结束", 390, 120);
            g.drawString("您的分数为：" + snakeInit.getWindowBot().getScore(), 365, 180);
        }
    }

    /**
     * 随机生成食物坐标
     */
    public void randomFood(){
        Random random = new Random();
        snakeInit.setFoodX(random.nextInt(920) / 20 * 20);
        snakeInit.setFoodY(random.nextInt(480) / 20 * 20);
        if (snakeInit.getFoodX()<20){
            snakeInit.setFoodX(20);
        }
        if (snakeInit.getFoodY()<20){
            snakeInit.setFoodY(20);
        }
        snakeInit.setIsFood(false);
    }

    public void setFail(boolean fail) {
        isFail = fail;
    }
}
