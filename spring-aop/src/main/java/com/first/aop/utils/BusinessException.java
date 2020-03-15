package com.first.aop.utils;


/**
 * 
 * <pre>
 * 订单业务异常
 * </pre>
 *
 * @author penglei
 * @version $Id: BusinessException.java, v 0.1 2014-6-7 上午11:42:50 Exp $
 */
public class BusinessException extends BaseException {

    /**
     * <pre>
     * 
     * </pre>
     */
    private static final long serialVersionUID = -4987001305340759909L;

    public BusinessException() {
        super();
    }

    public BusinessException(String errCode) {
        super(errCode);
    }

    public BusinessException(String errCode, String... params) {
        super(errCode, params); // 此处
    }

    public BusinessException(String errCode, String message) {
        super(errCode, message);
    }

    // state 常量
    /** 登陆超时状态 **/
    public static final Integer SESSION_TIMEOUT = -1;
    /** 失败状态 **/
    public static final Integer INPUT = 0;
    /** 成功状态 **/
    public static final Integer SUCCESS = 1;
    /** 失败状态使用全局提示 **/
    public static final Integer INPUT_AUTO = 2;
    /** 成功状态使用全局提示 **/
    public static final Integer SUCCESS_AUTO = 3;
    /** 参数校验错误，统一返回400 */
    public static final Integer PARAMS_ERROR = 400;

    /** [异常状态码]失败状态使用全局提示 用于结算页面tradpage特殊处理,需要弹出提示的时候返回购物车 **/
    public static final Integer TRAD_INPUT_AUTO = -999;
    /** [异常状态码]异步请求对于登陆的拦截 **/
    public static final Integer NOT_LOGIN = -998;
    /** [异常状态码]main.dec中不弹出，给页面自己处理 **/
    public static final Integer NOT_ALERT = -997;
    /**
     * [异常状态码]登陆已过期，需刷新页面
     */
    public static final Integer LOGIN_OUT_DATE = -996;
    /**
     * SOA运行时业务异常
     */
    public static final Integer SOA_BUSINESS_EXCPTION = -995;
    /**
     * SOA运行时非业务异常
     */
    public static final Integer SOA_RUNTIME_EXCPTION = -1001;

    /**
     * 
     * <pre>
     * 抛出业务逻辑异常信息
     * </pre>
     *
     * @param errCode 异常信息
     */
    public static void throwMessage(String errCode, String... params) {
        throw new BusinessException(String.valueOf(PARAMS_ERROR), errCode);
    }

    /**
     * 
     * <pre>
     * 抛出业务逻辑异常信息
     * </pre>
     *
     * @param errCode 异常信息
     */
     public static void throwMessageWithCode(String errCode, String message) {
         throw new BusinessException(errCode, message);
     }

}
