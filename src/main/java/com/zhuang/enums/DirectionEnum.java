package com.zhuang.enums;

/**
 * 方向枚举
 *
 * @module
 * @author zxd
 * @date 2022/10/21  11:55
**/
public enum DirectionEnum {
    /**
     * 左
     */
    LEFT(37),

    /**
     * 上
     */
    UP(38),

    /**
     * 右
     */
    RIGHT(39),

    /**
     * 下
     */
    DOWN(40);

    private final Integer code;

    DirectionEnum(Integer code){
        this.code=code;
    }

    public Integer getCode(){
        return this.code;
    }
}
