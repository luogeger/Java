package com.first.lambda;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author luoxiaoqing
 */
public class LambdaTest {

    private List<Person> personList;



    @Before
    public void before1() {
        personList = Arrays.asList(
                new Person( "Liu", "Bei",20),
                new Person("Guan", "Yu", 21),
                new Person( "Zhang", "Fei",22)
        );
    }


    @Test
    public void main1 () {
        personList.forEach(System.out::println);



    }


    /**
     *  lambda的演变过程， jdk1.8之前，方法无法复制给变量
     *  <pre>
     *      Object var = public void doSomething (String str) {
     *           System.out.printf(str);
     *      }
     *  </pre>
     */
    @Test
    public void main2 () {
        MyLambdaInterface myLambdaInterfaceImpl = new MyLambdaInterface() {
            @Override
            public void doSomething(String s) {
                System.out.println(s);
            }
        };
        myLambdaInterfaceImpl.doSomething("hello jdk1.7");

        // 完整的Lambda表达式声明：
        MyLambdaInterface lambdaImpl = (str) -> System.out.println(str);
        // 执行方法体
        lambdaImpl.doSomething("hello lambda");

    }


    @Test
    public void main3 () {

    }






}
