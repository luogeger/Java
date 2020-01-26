package com.company.set;

import org.junit.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;

/**
 * @author luoxiaoqing
 * @date 2020-01-24__21:57
 */
public class SetTest {
    
    @Test
    public void main1 () {

        HashSet<User> users = new HashSet<>();

        User a = new User(1, "tom");
        User b = new User(2, "tom");
        User c = new User(2, "tom");

        Collections.addAll(users, a, b, c);
        for (User user : users) {
            System.out.println(user);
        }

        //  不重写equals和hashCode都会被打印出来，




    }
}


class User {
    private Integer id;
    private String  name;

    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
