package com.zhuang.init;

import com.zhuang.component.GamePanel;
import com.zhuang.component.WindowBot;
import com.zhuang.constant.Constant;
import com.zhuang.enums.DirectionEnum;
import com.zhuang.snack.SnakeKeyListener;
import com.zhuang.snack.SnakeMove;
import com.zhuang.snack.SnakeNode;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;

/**
 * 游戏初始化
 *
 * @module
 * @author zxd
 * @date 2022/10/21  11:55
**/
public class SnakeInit extends JFrame {
    //游戏窗口
    private GamePanel jPanel;
    //底部按钮面板
    private WindowBot windowBot;
    //定时器
    private Timer timer;
    //游戏是否开始
    private boolean isStart;
    //蛇的身体节点集合
    private LinkedList<SnakeNode> snakeNodeList;
    //蛇的方向
    private Integer direction;
    //食物x坐标
    private int foodX;
    //食物y坐标
    private int foodY;
    //是否生成食物
    private boolean isFood;

    public SnakeInit(){
        //初始化窗口
        super("贪吃蛇");
        windowInit();
        //初始化默认参数
        isStart = false;
        isFood = true;
        //初始化蛇身
        snakeBodyInit();
        //初始化游戏面板
        jPanel = new GamePanel(this);
        add(jPanel);
        //初始化窗口底部
        windowBot = new WindowBot(this);
        add(windowBot);
        //创建定时器类
        timer = new Timer(Constant.SNAKE_SPEED,new SnakeMove(this));

        setVisible(true);
    }

    /**
     * 初始化窗口
     */
    private void windowInit(){
        setSize(Constant.WINDOW_WIDTH,Constant.WINDOW_HEIGHT);   //设置窗口大小
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //设置点击右上角关闭按钮后程序会停止，不设置的话关闭了程序不会停止
        setLocationRelativeTo(null);                     //设置启动时默认位置为屏幕居中
        setResizable(false);                             //设置窗口不可变大或缩小
        setLayout(null);
        //设置窗口的图标，先获取图片再设置
        BufferedImage read = null;
        try {
            URL url = this.getClass().getClassLoader().getResource(Constant.IMG_PATH);
            if (url!=null) {
                read = ImageIO.read(url);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        setIconImage(read);

        addKeyListener(new SnakeKeyListener());               //添加键盘监听
        setFocusable(true);
    }

    /**
     * 初始化蛇和方向
     */
    public void snakeBodyInit() {
        snakeNodeList = new LinkedList<>();
        direction = DirectionEnum.RIGHT.getCode();
        for (int i = 0; i < Constant.SNAKE_SIZE; i++) {
            SnakeNode snakeNode = new SnakeNode(100-((i+1)*20),120,Constant.SNAKE_WIDTH,Constant.SNAKE_HEIGHT);
            snakeNodeList.add(snakeNode);
        }
    }

    public Integer getDirection() {
        return direction;
    }

    public void setDirection(Integer direction) {
        this.direction = direction;
    }

    public LinkedList<SnakeNode> getSnackNodeList() {
        return snakeNodeList;
    }

    public int getFoodX() {
        return foodX;
    }

    public void setFoodX(int foodX) {
        this.foodX = foodX;
    }

    public int getFoodY() {
        return foodY;
    }

    public void setFoodY(int foodY) {
        this.foodY = foodY;
    }

    public boolean getIsFood() {
        return isFood;
    }

    public void setIsFood(boolean food) {
        isFood = food;
    }

    public boolean getIsStart() {
        return isStart;
    }

    public void setIsStart(boolean start) {
        isStart = start;
    }

    public Timer getTimer() {
        return timer;
    }

    public GamePanel getJPanel() {
        return jPanel;
    }

    public WindowBot getWindowBot() {
        return windowBot;
    }
}
