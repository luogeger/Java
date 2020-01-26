package com.first.string;

import java.util.Date;

public class B_time_replace {
    public static void main(String[] args) {
        String fileName = new Date().toString().substring(11, 19);
        fileName = fileName.replace(":", "-");
        System.out.println(fileName);
    }
}
