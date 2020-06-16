package com.first.map;

import com.first.entity.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    
    @Test
    public void main1 () {
        List<String> list = new ArrayList<String>();
        list.add("a");
        list.add("a");
        list.add("d");
        list.add("f");
        list.add("v");

        Map<String, List<String>> collect = list.stream().collect(Collectors.groupingBy(v -> v));
        System.out.println(collect.toString());
        // {a=[a, a], d=[d], v=[v], f=[f]}
    }


    @Test
    public void main2 () {
        List<User> list = new ArrayList<>();
        list.add(new User("a", 18));
        list.add(new User("c", 18));
        list.add(new User("a", 19));
        list.add(new User("c", 20));
        list.add(new User("c", 19));

        Map<String, List<User>> collect = list.stream().collect(Collectors.groupingBy(User::getName, Collectors.toList()));
        System.out.println(collect);
        // {a=[User(userID=null, isVip=false, balance=0, name=a, age=18), User(userID=null, isVip=false, balance=0, name=a, age=19)], c=[User(userID=null, isVip=false, balance=0, name=c, age=18), User(userID=null, isVip=false, balance=0, name=c, age=20), User(userID=null, isVip=false, balance=0, name=c, age=19)]}
        // {a=[18, 19], c=[18, 20, 19]}

    }
}
