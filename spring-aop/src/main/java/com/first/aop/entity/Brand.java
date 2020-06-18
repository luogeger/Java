package com.first.aop.entity;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class Brand {


    private Integer id;

    @NotNull(message = "不能缺少name")
    private String  name;
    
    private String  image;
    
    private String  letter;
}
