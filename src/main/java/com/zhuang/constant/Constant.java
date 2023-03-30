package com.zhuang.constant;

/**
 * 基础配置
 *
 * @module
 * @author zxd
 * @date 2022/10/21  11:55
**/
public interface Constant {
    /**
     * 窗口宽度
     */
    int WINDOW_WIDTH = 970;

    /**
     * 窗口高度
     */
    int WINDOW_HEIGHT = 600;

    /**
     * 游戏面板高度
     */
    int GAME_WIDTH = 970;

    /**
     * 游戏面板宽度
     */
    int GAME_HEIGHT = 520;

    /**
     * 底部面板宽度
     */
    int BOT_WIDTH = 970;

    /**
     * 底部面板高度
     */
    int BOT_HEIGHT = 50;

    /**
     * 蛇的宽度
     */
    int SNAKE_WIDTH = 19;

    /**
     * 蛇的高度
     */
    int SNAKE_HEIGHT = 19;

    /**
     * 蛇的初始长度
     */
    int SNAKE_SIZE = 3;

    /**
     * 蛇的初始速度，越低越快
     */
    int SNAKE_SPEED = 200;

    /**
     * 一个食物的分数
     */
    int FOOD_SCORE = 10;

    /**
     * 最高等级
     */
    int LEVEL_MAX = 5;

    /**
     * 多少个食物增加一级
     */
    int UP_LEVEL_NUM = 10;

    /**
     * 图标路径
     */
    String IMG_PATH = "image/snake.png";


}
