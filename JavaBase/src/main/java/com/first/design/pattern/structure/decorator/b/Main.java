package com.first.design.pattern.structure.decorator.b;

import org.junit.Test;

/**
 * @author luoxiaoqing
 * @date 2020-02-11__22:08
 */
public class Main {
    /**
     * 装饰者模式（包装模式）
     * 此时，没有用到抽象类，但是会发现所有的装饰类，都出现了重复的代码，可以抽取到抽象类里面。
     * 从而继承抽象类，让抽象类去实现接口
     *
     */


    @Test
    public void main () {
        //  牛奶
        Milk milk = new Milk();
        System.out.println(milk.money() + milk.desc());

        //  +冰
        Ice iceMilk = new Ice(milk);
        System.out.println(iceMilk.money() + iceMilk.desc());

        //  +冰 +糖
        Sugar sugarIceMilk = new Sugar(iceMilk);
        System.out.println(sugarIceMilk.money() + sugarIceMilk.desc());

        //  +冰 +糖 +盐
        Salt sugarIceSaltMilk = new Salt(sugarIceMilk);
        System.out.println(sugarIceSaltMilk.money() + sugarIceSaltMilk.desc());

        System.out.println("咖啡 ====================");
        Coffee coffee = new Coffee();
        System.out.println(coffee.money() + coffee.desc());

        Sugar sugarCoffee = new Sugar(coffee);
        System.out.println(sugarCoffee.money() + sugarCoffee.desc());
    }





    private void test (int a, int b) {
        boolean condition = false , strategy = false;

        if (a == 1) {
            condition = true;
        }

        if (b == 2) {
            strategy = true;
        }

        if (condition || strategy) {
            System.out.println("");
        }
    }



















}
