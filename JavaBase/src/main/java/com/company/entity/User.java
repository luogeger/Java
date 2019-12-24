package com.company.entity;


import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class User {
    private String userID;
    private boolean isVip;
    private int balance;
    private String  name;
    private Integer age;

}
