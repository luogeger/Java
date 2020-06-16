package com.first.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class User {
    private String userID;
    private boolean isVip;
    private int balance;
    private String  name;
    private Integer age;

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

}
