package com.first.lambda;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author luoxiaoqing
 */
public class LambdaTest {

    private List<Person> personList;


    /**
     * 准备数据
     */
    @Before
    public void before1() {
        personList = Arrays.asList(
                new Person( "Liu", "Bei",20),
                new Person("Guan", "Yu", 21),
                new Person( "Zhang", "Fei",22)
        );
    }


    /**
     * 测试数据
     */
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

        // 完整的Lambda表达式声明：其实Lambda就是个匿名类，只不过这个匿名类实现的接口只有一个抽象方法，我们只需要编写这个方法体就行了。
        MyLambdaInterface lambdaImpl = (str) -> System.out.println(str);
        // 执行方法体
        lambdaImpl.doSomething("hello lambda");


    }


    /**
     * 仿写Lambda
     */
    @Test
    public void main3 () {
        MyCheckName myCheckName = (Person p) -> StringUtils.startsWith( p.getFirstName(), "Z");
        MyExecutor myExecutor = (Person person) -> System.out.println(person.getFirstName());

        checkEndExecutor(personList, myCheckName, myExecutor);
    }

    /**
     *
     * @param list
     * @param checkName
     * @param executor
     */
    public static void checkEndExecutor(List<Person> list, MyCheckName checkName, MyExecutor executor) {
        for (Person person : list) {
            Boolean flag = checkName.check(person);
            if (flag) {
                executor.print(person);
            }
        }
    }






}
