package com.company.e_stream;

import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {

    @Test
    public void jdbc() throws Exception {
        // 1.  注册数据库驱动
        // 2.  获取数据库连接
        // 3.  创建发送SQL对象
        // 4.  执行SQL语句，获取结果
        // 5.  遍历结果集
        // 6.  关闭资源
        String url = "wanzhanqu.mysql.rds.aliyuncs.com/sungoal_public";
        String user = "sungoal_public";
        String pwd = "sun85ydhfAiu1";
        String sql = "select * from brand";

        //DriverManager.registerDriver();
        Connection conn = DriverManager.getConnection(url, user, pwd);
        Statement stt = conn.createStatement();
        ResultSet result = stt.executeQuery(sql);

        while (result.next()) {
            /*int id = result.getInt("id");
            String _user = result.getString("username");
            String _pwd = result.getString("password");
            System.out.println(id + " -> " + _user + " -> " + _pwd);*/
            System.out.println(result);
        }

        result.close();
        stt.close();
        conn.close();

        //return result;
    }

    @Test
    public void test1() {
//        Stream<String> flow = Stream.of("11", "22", "33", "44", "55");
//        flow.stream().map(Integer::parseInt).forEach(System.out::println);

//        String [] list = {"11", "22", "33", "44", "55"};// cannot resolve method 'stream()'
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, "11", "22", "33", "44", "66");
        list.stream().map(Integer::parseInt).forEach(System.out::println);// 把String转换为Integer

        long count = list.stream().map(Integer::parseInt).filter(item -> item > 30).count();
        System.out.println(count);
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
    public void test4() {
        Integer[] nums = {1, 34, 56, 234, 78, 97, 9};// int[] nums = {}; 报错
        Stream<Integer> numStream = Stream.of(nums);
        List<String> list = numStream.map(String::valueOf).collect(Collectors.toList());// Integer转换String
        list.stream().forEach(System.out::println);

    }

    @Test
    public void test3() {
        class User {
            private String userID;
            private boolean isVip;
            private int balance;

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
                        .sorted((t1, t2) -> t2.getBalance() - t1.getBalance())// 降序
                        .limit(3)
                        .map(User::getUserID)
                        .collect(Collectors.toList());
        array.forEach(System.out::println);
    }

    @Test
    public void test5() {
        Integer[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        List<Integer> collect = Stream.of(nums)
                .filter((n) -> {
                    return n > 5 && n % 2 != 0;
                })
                .collect(Collectors.toList());
        System.out.println(collect);
        for (Integer i : collect) {
            System.out.println(i);
        }
    }

    @Test
    public void test6() {

        List<Integer> ids = new ArrayList<>();
        Collections.addAll(ids, 51, 52, 59, 60, 62, 70, 71, 75);

        List<Integer> nums = new ArrayList<>();
        Collections.addAll(nums, 0,0,51,51,52,56,57,58);// 所有父节点id

        List<Integer> collect = nums.stream().distinct().filter(item -> item != 0).collect(Collectors.toList());
        //collect.stream().forEach(System.out::println);


        // 去重
        //Collection<Integer> parent = CollectionUtils.removeAll(nums, ids);
        //parent.stream().forEach(System.out::println);

    }
}