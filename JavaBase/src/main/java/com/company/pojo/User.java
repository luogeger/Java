package com.company.pojo;


import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class User {
    private String userID;
    private boolean isVip;
    private int balance;

    public User() {
    }

    public User(String userID, boolean isVip, int balance) {
        this.userID = userID;
        this.isVip = isVip;
        this.balance = balance;
    }

    public boolean isVip() {
        return this.isVip;
    }

    public String getUserID() {
        return this.userID;
    }

    public int getBalance() {
        return this.balance;
    }
}
