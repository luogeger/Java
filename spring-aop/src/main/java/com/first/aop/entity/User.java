package com.first.aop.entity;

import lombok.Data;

import java.util.Date;

@Data
public class User extends BaseEntity {
    private String  userName;
    private String  password;
    private String  phone;
    private Date    created;
    private String  salt;

}
