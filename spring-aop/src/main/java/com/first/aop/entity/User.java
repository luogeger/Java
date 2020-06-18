package com.first.aop.entity;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class User extends BaseEntity {

    @NotNull(message = "not userName")
    private String  userName;
    private String  password;
    private String  phone;
    private Date    created;
    private String  salt;

    
}
