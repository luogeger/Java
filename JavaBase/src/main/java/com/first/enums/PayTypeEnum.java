package com.first.enums;

/**
 * @author luoxiaoqing
 */
public enum PayTypeEnum {
    /**
     * 1-现金
     */
    CASH(1, "CASH","现金"),
    /**
     * 2-微信
     */
    WECHAT(2, "WECHAT","微信"),
    /**
     * 3-支付宝
     */
    ALIPAY(3, "ALIPAY","支付宝"),
    /**
     * 4-银联
     */
    UNIONPAY(4, "UNIONPAY","银联"),
    /**
     * 5-POS刷卡
     */
    POS(5, "POS","POS刷卡"),
    /**
     * 6-银行转账
     */
    BANK(6, "BANK","银行转账"),
    /**
     * 7-记账宝
     */
    ACCOUNTING_TREASURE(7,"ACCOUNTING_TREASURE", "记账宝"),
    /**
     * 其他
     */
    OTHER(9,"OTHER", "其他"),
    /**
     * 挂账-特殊枚举，只作为前台展示用，实际不会有挂账的payType入库
     */
    ON_ACCOUNT(100,"ON_ACCOUNT", "挂账");
    private Integer code;
    private String  desc;
    private String  name;

    PayTypeEnum(Integer code, String name, String desc) {
        this.code = code;
        this.name = name;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }


    /**
     * 根据code获取desc
     * @param code
     * @return
     */
    public static String getValueByCode(Integer code){
        if(code == null){
            return null;
        }
        for(PayTypeEnum obj: PayTypeEnum.values()){
            if(code.equals(obj.getCode())){
                return obj.getDesc();
            }
        }
        return  null;
    }
}



