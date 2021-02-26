package com.first.string;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    @Test
    public void main4 () {
        List<String> list = Arrays.asList("1999-01","2003-11","1997-10","2010-03");

        // 默认是升序
        System.out.println("----- 升序");
        System.out.println("min: "+list.stream().min(Comparator.naturalOrder()).get());
        System.out.println("max: "+list.stream().max(Comparator.naturalOrder()).get());
        System.out.println("----- 降序");
        System.out.println("min: "+list.stream().min(Comparator.reverseOrder()).get());
        System.out.println("max: "+list.stream().max(Comparator.reverseOrder()).get());

    }


    @Test
    public void main3 () {
        List<String> list = Arrays.asList("1999-01","2003-11","1997-10","2010-03");

        String asc = list.stream()
                .sorted((e1, e2) -> {return e1.compareTo(e2);})
                .findFirst()
                .get();
        System.out.println(asc);

        String desc = list.stream()
                .sorted((e1, e2) -> {return e2.compareTo(e1);})
                .findFirst()
                .get();
        System.out.println(desc);
    }


    
    
    @Test
    public void main2 () {
//        System.out.println(StringTool.compare("1993-09", "2020-01"));
//        System.out.println(StringTool.compare("1993-09", "1993-09"));
        List<String> list = Arrays.asList("1999-01","2003-11","1997-10","2010-03");
        System.out.println("升序： 默认是可以不写的， (e1, e2)-> e1.compareTo(e2) ==  String::compareTo");
        List<String> collect = list.stream()
                //  默认是升序
                .sorted(String::compareTo)
                .collect(Collectors.toList());
        collect.forEach(System.out::println);
        System.out.println("降序：Comparator.reverseOrder()   ==  (e1, e2)-> e2.compareTo(e1)");
        List<String> collect1 = list.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        collect1.forEach(System.out::println);
    }

    @Test
    public void main1() {
        String date = new Date().toString().substring(11, 19);
        date = date.replace(":", "-");
        System.out.println(date);

    }

    @Test
    public void bufferReader() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("test.txt"));
        System.out.println(br.readLine());

    }


    @Test
    public void test1() {
        String s = "28349";
        String[] ids = s.split("\\.");
        System.out.println(ids.length);

    }

    @Test
    public void test2() {
        StringBuilder sb = new StringBuilder();
        sb.append("a/d/s/s/c/").delete(sb.length() - 1, sb.length());


    }

    @Test
    public void test3() {
        String node = "92646.234.11.34";
        int i1 = node.lastIndexOf(".");
        String substring = node.substring(0, i1);

        String[] ids = node.split("\\.");


        System.out.println(ids[ids.length - 2]);
        StringBuilder parentId = new StringBuilder();
        for (int i = 0; i < ids.length - 1; i++) {
            parentId.append(ids[i] + ".");
        }
        parentId.delete(parentId.length() - 1, parentId.length());
        System.out.println(parentId);

    }


    @Test
    public void test4() {
        //String [] aa = {"q", "w", "e", "r", "c"};
        //String [] bb = {"a", "w", "e", "r", "t"};

        ArrayList<String> aa = new ArrayList<>();
        ArrayList<String> bb = new ArrayList<>();

        Collections.addAll(aa, "q", "w", "e", "r", "c");
        Collections.addAll(bb, "a", "w", "e", "r", "t");


        for (String a : aa) {
            if (!bb.contains(a)) {
                System.out.println(a);
            }
        }
        System.out.println("===");
        for (String b : bb) {
            if (!aa.contains(b)) {
                System.out.println(b);
            }

        }

    }
}
