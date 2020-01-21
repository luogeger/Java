package com.company.list;

import lombok.Data;
import lombok.experimental.Accessors;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author luoxiaoqing
 * @date 2020-01-08__10:20
 * @desc
 */
@Data
@Accessors(chain = true)
public class Reflect {

    /**
     *  Integer类型的列表，中文
     */
    @Test
    public void test1 () {
        ArrayList<Integer> list = new 	ArrayList<>();
        list.add(2);
        list.add(3);
        try {
            //todo ,get hot score
            list.add(60);
            list.getClass().getMethod("add", Object.class).invoke(list, "活跃度中等【0~100】");
            //todo get fans
            list.add(1500);
            list.getClass().getMethod("add", Object.class).invoke(list, "粉丝数,排名 3689 位");

            //todo get evaluation
            list.add(90);
            list.getClass().getMethod("add", Object.class).invoke(list, "用户评价,超越 92%的用户");

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(Arrays.toString(list.toArray()));
    }


    /**
     * linkedList 可以装重复元素
     */
    @Test
    public void test2 () {
        LinkedList<String> link = new LinkedList<String>();
        //添加元素
        link.addFirst("abc1");
        link.addFirst("abc2");
        link.addFirst("abc3");
        link.addFirst("abc3");
        link.addFirst("abc3");

        System.out.println(link);
    }
}
