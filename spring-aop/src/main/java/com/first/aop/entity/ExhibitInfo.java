package com.first.aop.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2019/4/25 0025.
 */
@Data
public class ExhibitInfo extends BaseEntity{
    /**展品编号 15位 EB+年月日小时8位+5位随机数字*/
    private String exhibitCode;
    /**展品名称*/
    private String exhibitName;
    /**展品类型（类目第二层）*/
    private Integer exhibitionType;
    /**品牌类型（类目第四层）*/
    private Integer brandType;
    /**品牌ID*/
    private Integer brandId;
    /**展品简介*/
    private String exhibitDesc;
    /**展会ID*/
    private Integer exhibitionId;
    /**商品ID*/
    private Integer commodityId;
    /**创建归属公司ID，即用户ID*/
    private Integer companyId;
    /**厂家建议零售价格范围（万元）*/
    private BigDecimal exhibitMsrp;
    /** 综合优惠（万元）*/
    private BigDecimal preferential;
    /**是否参展 0：不参展 1：参展*/
    private Integer isShow;

}
