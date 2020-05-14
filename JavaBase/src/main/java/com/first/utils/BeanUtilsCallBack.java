package com.first.utils;

import lombok.Data;
import lombok.experimental.Accessors;


/**
 * @author luoxiaoqing
 * @date 2020-05-14__16:07
 * @desc
 */
@FunctionalInterface
public interface BeanUtilsCallBack<S, T> {
    /**
     * 定义默认回调方法
     * @param t tt
     * @param s ss
     */
    void callBack(S t, T s);
}
