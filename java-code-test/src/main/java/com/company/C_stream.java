package com.company;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class C_stream {
    @Test
    public void test1() {
//        Stream<String> flow = Stream.of("11", "22", "33", "44", "55");
//        flow.stream().map(Integer::parseInt).forEach(System.out::println);

//        String [] list = {"11", "22", "33", "44", "55"};// cannot resolve method 'stream()'
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, "11", "22", "33", "44", "66");
        list.stream().map(Integer::parseInt).forEach(System.out::println);// 把String转换为Integer
    }

    @Test
    public void test2() {
        //创建集合对象
        List<String> list = new ArrayList<>();
        //向集合中添加数据
        list.add("张无忌");
        list.add("周芷若");
        list.add("赵敏");
        list.add("张强");
        list.add("张三丰");
        //使用Stream流完成对集合的上述三个操作
        //list.stream();表示获取Stream流的对象
        list.stream()
                .filter(s -> s.startsWith("张"))//首先筛选所有姓张的人
                .filter(s -> s.length() == 3)//然后筛选名字有三个字的人
                .forEach(System.out::println);//最后进行对结果进行打印输出

        List<String> result = list.stream()
                .filter(s -> s.startsWith("张"))
                .filter(s -> s.length() == 3)
                .collect(Collectors.toList());// 转存到新集合
        System.out.println(result.toString());
    }

    @Test
    public void test3() {
        class User
        {
            private String userID;
            private boolean isVip;
            private int balance;

            public User(String userID, boolean isVip, int balance)
            {
                this.userID = userID;
                this.isVip = isVip;
                this.balance = balance;
            }

            public boolean isVip()
            {
                return this.isVip;
            }

            public String getUserID()
            {
                return this.userID;
            }

            public int getBalance()
            {
                return this.balance;
            }
        }

        ArrayList<User> users = new ArrayList<>();
        users.add(new User("2017001", false, 0));
        users.add(new User("2017002", true, 36));
        users.add(new User("2017003", false, 98));
        users.add(new User("2017004", false, 233));
        users.add(new User("2017005", true, 68));
        users.add(new User("2017006", true, 599));
        users.add(new User("2017007", true, 1023));
        users.add(new User("2017008", false, 9));
        users.add(new User("2017009", false, 66));
        users.add(new User("2017010", false, 88));


        //Stream API实现方式
        //也可以使用parallelStream方法获取一个并发的stream，提高计算效率
        Stream<User> stream = users.stream();
        List<String> array =
                stream.filter(User::isVip)
                        .sorted((t1, t2) -> t2.getBalance() - t1.getBalance()).limit(3)
                        .map(User::getUserID)
                        .collect(Collectors.toList());
        array.forEach(System.out::println);
    }
}
