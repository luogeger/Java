package com.first.enums;

/**
 * 策略枚举
 *
 * @author luoxiaoqing
 */
public enum StrategyEnum {


    /**
     * 临时工
     */
    TEMP(-1){
        @Override
        int validation() {
            return 0;
        }
    },


    /**
     * 老板
     */
    BOSS(0) {
        @Override
        int validation() {
            return 100000;
        }
    },


    /**
     * 领导
     */
    LEADER(1) {
        @Override
        int validation() {
            return 50000;
        }
    },

    /**
     * 员工
     */
    STAFF(2) {
        @Override
        int validation() {
            return 10000;
        }
    };


    /**
     * 抽象方法
     * @return
     */
    abstract int validation();

    /**
     *
     */
    private final int position;

    /**
     * 构造器
     * @param position
     */
    StrategyEnum(int position) {
        this.position = position;
    }

    /**
     *
     * @param position
     * @return
     */
    public static StrategyEnum getaa(int position) {

        for (StrategyEnum strategyEnum : StrategyEnum.values()) {
            if (strategyEnum.position == position) {
                return strategyEnum;
            }
        }
        return null;
    }


}
