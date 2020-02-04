package com.first.designPattern.behavior.templateMethod;

/**
 * 模板方法
 *
 * @author luoxiaoqing
 * @date 2018-01-10__15:47
 * @desc
 */
public class TemplateMethodPattern {
    public static void main(String[] args) {
        AbstractClass tm = new ConcreteClass();
        tm.TemplateMethod();
    }

}


/**
 * 抽象类
 */
abstract class AbstractClass {
    /**
     * 模板方法
     */
    public void TemplateMethod() {
        SpecificMethod();
        abstractMethod1();
        abstractMethod2();
    }

    /**
     * 具体方法
     */
    public void SpecificMethod() {
        // 1 - 10的随机数
        int random = (int) (Math.random() * 10 + 1);

        System.out.println(random);
        System.out.println("抽象类中的具体方法被调用...");
        if (random > 2) {
            return;
        }


    }

    /**
     * 抽象方法1
     */
    public abstract void abstractMethod1();


    /**
     * 抽象方法2
     */
    public abstract void abstractMethod2();
}


/**
 * 具体子类
 */
class ConcreteClass extends AbstractClass {

    @Override
    public void abstractMethod1() {
        System.out.println("抽象方法1的实现被调用...");
    }

    @Override
    public void abstractMethod2() {
        System.out.println("抽象方法2的实现被调用...");
    }
}