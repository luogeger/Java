package com.company.a_string;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.stream.Stream;

public class A_String_split {
    public static void main(String[] args) {
        String str = "1. slkdfjlksadfj";
        char c = str.charAt(0);
        System.out.println(c);

        byte b = (byte)101;
        System.out.println(b);


    }

    @Test
    public void test1 () {
        String s = "28349";
        String[] ids = s.split("\\.");
        System.out.println(ids.length);

    }

    @Test
    public void test2 () {
        StringBuilder sb = new StringBuilder();
        sb.append("a/d/s/s/c/").delete(sb.length() -1, sb.length());


    }

    @Test
    public void test3 () {
        String node = "92646.234.11.34";
        int i1 = node.lastIndexOf(".");
        String substring = node.substring(0, i1);


        String[] ids = node.split("\\.");









        System.out.println(ids[ids.length -2]);
        StringBuilder parentId = new StringBuilder();
        for (int i = 0; i < ids.length -1; i++) {
            parentId.append(ids[i]+".");
        }
        parentId.delete(parentId.length() -1, parentId.length());
        System.out.println(parentId);

    }


    @Test
    public void test4 () {
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

    @Test
    public void test5 () {
    }







}
