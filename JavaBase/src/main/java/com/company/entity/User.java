package com.company.entity;


import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Objects;

@Data
@Accessors(chain = true)
public class User {
    private String userID;
    private boolean isVip;
    private int balance;
    private String  name;
    private Integer age;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return isVip == user.isVip &&
                balance == user.balance &&
                Objects.equals(userID, user.userID) &&
                Objects.equals(name, user.name) &&
                Objects.equals(age, user.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID, isVip, balance, name, age);
    }
}
