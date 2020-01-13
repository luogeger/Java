package com.company.string;

import java.io.BufferedReader;
import java.io.FileReader;

public class C_bufferedReader {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("test.txt"));
        System.out.println(br.readLine());
    }
}
