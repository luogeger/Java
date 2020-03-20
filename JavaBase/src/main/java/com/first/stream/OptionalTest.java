package com.first.stream;

import com.first.entity.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


public class OptionalTest {

    // of	        把指定的值封装为Optional对象，如果指定的值为null，则抛出NullPointerException
    // empty	    创建一个空的Optional对象
    // ofNullable	把指定的值封装为Optional对象，如果指定的值为null，则创建一个空的Optional对象
    // get	        如果创建的Optional中有值存在，则返回此值，否则抛出NoSuchElementException
    // orElse	    如果创建的Optional中有值存在，则返回此值，否则返回一个默认值
    // orElseGet	如果创建的Optional中有值存在，则返回此值，否则返回一个由Supplier接口生成的值
    // orElseThrow	如果创建的Optional中有值存在，则返回此值，否则抛出一个由指定的Supplier接口生成的异常
    // filter	    如果创建的Optional中的值满足filter中的条件，则返回包含该值的Optional对象，否则返回一个空的Optional对象
    // map	        如果创建的Optional中的值存在，对该值执行提供的Function函数调用
    // flagMap	    如果创建的Optional中的值存在，就对该值执行提供的Function函数调用，返回一个Optional类型的值，否则就返回一个空的Optional对象
    // isPresent	如果创建的Optional中的值存在，返回true，否则返回false
    // ifPresent	如果创建的Optional中的值存在，则执行该方法的调用，否则什么也不做

    @Test
    public void test1() {

        //创建一个值为张三的String类型的Optional
        Optional<String> ofOptional = Optional.of("张三");
        //如果我们用of方法创建Optional对象时，所传入的值为null，则抛出NullPointerException如下图所示
        Optional<String> nullOptional = Optional.of(null);
        // 报错：java.lang.NullPointerException
    }

    @Test
    public void test2() {
        //为指定的值创建Optional对象，不管所传入的值为null不为null，创建的时候都不会报错
        Optional<Object> o1 = Optional.ofNullable(null);
        Optional<String> o2 = Optional.ofNullable("lucy");

        System.out.println(o1);
        // Optional.empty

    }


    /**
     * 判断两个Optional是否相等，主要是所包裹的对象是否相等
     */
    @Test
    public void test3() {
        Optional op1 = Optional.of("VARIABLE");
        Optional op2 = Optional.ofNullable("VARIABLE");
        System.out.println(op1.equals(op2));
        // true

        Optional<User> u1 = Optional.of(new User());
        Optional<User> u2 = Optional.of(new User());
        System.out.println(u1.equals(u2));
        // false
    }

    /**
     * 解决list为null的方案
     * @param args
     */
    public static void main(String[] args) {
        ArrayList<String> ss = new ArrayList<>();
        Collections.addAll(ss, "a", "b");


        //  还是会报错：Error:(75, 51) java: 未报告的异常错误java.lang.Throwable; 必须对其进行捕获或声明以便抛出
//        Optional.ofNullable(getList()).orElseThrow(()->{
//            throw new RuntimeException("有异常");
//        });
        Optional.ofNullable(getList()).orElseGet(() -> ss).stream().forEach(System.out::println);
    }

    public static List<String> getList() {
        ArrayList<String> list = new ArrayList<>();
        return null;
    }
}
