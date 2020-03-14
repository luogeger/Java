package com.first.aop.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Product {
    private Integer id;
    private Integer spuId;
    private String  title;
    private String  images;
    private Integer indexes;
    private String  ownSpec;
    private Boolean enable;
    private Date    createTime;
    private Date    lastUpdateTime;


}
