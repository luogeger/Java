package com.first.lombok;

import lombok.Data;
import lombok.experimental.Accessors;
import org.junit.Test;

/**
 * @author luoxiaoqing
 * @date 2020-05-20__08:27
 * @desc
 */
@Data
@Accessors(chain = true)
public class Main {
    @Test
    public void main1 () {

        User u1 = new User();
        u1.setAge(1);
        u1.setId("1");
        u1.setName("1");
        u1.setPlay("1");


        User u2 = new User();
        u2.setAge(1);
        u2.setId("1");
        u2.setName("1");
        u2.setPlay("2");

        System.out.println(u1.equals(u2));


    }
}
