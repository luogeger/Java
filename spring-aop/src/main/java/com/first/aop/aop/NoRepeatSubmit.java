package com.first.aop.aop;

import java.lang.annotation.*;

/**
 *  针对Controller的Action拦截，关键字是RequestMapping第一个value,如无value将不进行拦截
 *
 * @author luoxiaoqing
 * @date 2016/10/31
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NoRepeatSubmit {

    /**
     * 默认过期时间是1000毫秒,在1000毫秒内只能操作一次
     * @return
     */
    long millisecond() default 1000;
}
