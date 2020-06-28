package com.first.trycatch;

import com.first.lombok.User;
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

    @Test
    public void main2 () {
        User user = getUser();
        System.out.println(user);
    }

    private User getUser() {
        User user = new User();

        try {
            user.setAge(1);
            int i = 1/0;
            user.setName("lili");
        } catch (Exception e) {
            user.setName("lucy");
        }
        return user;
    }


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
