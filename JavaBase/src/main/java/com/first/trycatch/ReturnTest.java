package com.first.trycatch;

import org.junit.Test;

/**
 * @author luoxiaoqing
 */
public class ReturnTest {

    @Test
    public void main1 () {
        int a;
        System.out.println(calc());

    }

    /**
     *
     * @return  返回的false
     */
    private static int calc() {
        int a;
        try {
            int i = 1/1;
            a = 1+1;
            return sum(a);
        } catch (Exception e) {
            a = 1 +2 ;
            e.printStackTrace();
            return 2;
        } finally {
            System.out.println("finally");
        }


    }

    private static int sum(int i) {
        i = 3+ 5;
        return i;
    }


}
