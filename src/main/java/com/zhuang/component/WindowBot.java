package com.zhuang.component;

import com.zhuang.constant.Constant;
import com.zhuang.enums.GameBtnEnum;
import com.zhuang.init.SnakeInit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 窗口底部组件
 *
 * @module
 * @author zxd
 * @date 2022/10/24  9:44
**/
public class WindowBot extends JPanel {
    //游戏初始化对象
    private SnakeInit snakeInit;
    //分数
    private int score;
    //分数组件
    private JLabel scoreLabel;
    //等级组件
    private JLabel levelLabel;
    //当前等级
    private int level;
    //开始按钮
    private JButton button;
    //开始按钮，0开始游戏，1暂停游戏，2继续游戏，3重新开始
    private Integer beginBtn;

    public WindowBot(SnakeInit snakeInit) {
        this.snakeInit = snakeInit;
        this.beginBtn = GameBtnEnum.BEGIN.getCode();
        //设置默认分数等级
        score = 0;
        level = 1;
        //取消默认布局，自定义组件位置
        setLayout(null);
        setBounds(0,520, Constant.BOT_WIDTH,Constant.BOT_HEIGHT);
        setBackground(new Color(0xA4E7AD69,true));
        //开始按钮
        setBeginBtn();
        //分数
        setScore();
        //等级
        setLevel();
    }

    /**
     * 开始游戏按钮
     */
    public void setBeginBtn() {
        button = new JButton(GameBtnEnum.getByCode(beginBtn));                  //创建一个按钮
        button.setBounds(400, 10, 155, 30);               //设置按钮位置大小
        button.setFont(new Font("宋体", Font.PLAIN, 16));       //设置按钮字体的格式
        button.setBackground(new Color(0xA4E7AD69));                   //设置按钮颜色
        add(button);                                         //把按钮添加到面板中
        button.addActionListener(new ActionListener() {                  //设置监听，当按钮被点击时就会执行此方法
            @Override
            public void actionPerformed(ActionEvent e) {
                //取消按钮的聚焦，否则键盘监听会失效
                button.setFocusable(false);
                //如果是点击的开始游戏，将isStart设置为true，并且启动定时器
                if (beginBtn.equals(GameBtnEnum.BEGIN.getCode())) {
                    snakeInit.setIsStart(true);
                    snakeInit.getTimer().start();
                    reBtn(GameBtnEnum.STOP.getCode());
                } else if (beginBtn.equals(GameBtnEnum.STOP.getCode())) {
                    snakeInit.getTimer().stop();
                    reBtn(GameBtnEnum.CONTINUE.getCode());
                } else if (beginBtn.equals(GameBtnEnum.CONTINUE.getCode())) {
                    snakeInit.getTimer().start();
                    reBtn(beginBtn = GameBtnEnum.STOP.getCode());
                }else {
                    //重新开始
                    restart();
                }
                snakeInit.repaint();
            }
        });
    }

    /**
     * 分数标签
     */
    private void setScore(){
        scoreLabel = new JLabel("分数："+score);          //创建一个标签
        scoreLabel.setBounds(18,10,200,30);     //标签位置和大小
        scoreLabel.setFont(new Font("宋体",Font.PLAIN,30));
        add(scoreLabel);                              //将标签添加到面板中
    }

    /**
     * 等级标签
     */
    private void setLevel(){
        levelLabel = new JLabel("等级："+level);
        levelLabel.setBounds(800,10,200,30);
        levelLabel.setFont(new Font("宋体",Font.PLAIN,30));
        add(levelLabel);
    }

    /**
     * 增加分数
     * @param score 增加的分数
     */
    public void addScore(Integer score) {
        this.score+=score;
        remove(scoreLabel);
        setScore();
        snakeInit.repaint();
    }

    /**
     * 增加等级
     */
    public void addLevel(){
        this.level++;
        remove(levelLabel);
        setLevel();
        snakeInit.repaint();
    }

    private void restart(){
        //初始化蛇
        snakeInit.snakeBodyInit();
        //重新设置分数和等级
        score = 0;
        level = 1;
        reBtn(GameBtnEnum.BEGIN.getCode());
        snakeInit.getJPanel().setFail(false);
        snakeInit.getTimer().setDelay(Constant.SNAKE_SPEED);
        snakeInit.repaint();
    }

    /**
     * 重置按钮
     */
    public void reBtn(Integer text){
        //查询是否有存在的按钮
        if (isAncestorOf(button)) {
            remove(button);
            this.beginBtn = text;
            setBeginBtn();
        }
    }

    public Integer getScore() {
        return score;
    }

    public void setBeginBtn(int beginBtn) {
        this.beginBtn = beginBtn;
    }

    public JButton getButton() {
        return button;
    }
}
