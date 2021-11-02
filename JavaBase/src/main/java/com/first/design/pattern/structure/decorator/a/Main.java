package com.first.design.pattern.structure.decorator.a;

import org.junit.Test;

/**
 * @author luoxiaoqing
 * @date 2020-02-11__22:08
 */
public class Main {
    /**
     * 装饰者模式（包装模式）
     * 1，动态的给对象添加职责，（对一个方法或对象增加功能，相对于继承来说，继承是属于静态增加功能）
     * 2，可以在程序运行期间增强和撤销职责
     * 3，独立扩展，避免类爆炸
     *
     *
     *
     * 组成部分：
     * 1， 抽象组件（Component）：接口，规范准备接收附加责任的对象。
     *      谁要被增加，就是为谁准备的
     * 2， 被装饰者（ ConcreteComponent)：要装饰的具体对象，Component的实现类
     *      需要被装饰的对象
     * 3， 装饰器（Decorator）：持有Component对象的实例引用，该类的职责就是为了装饰具体组件对象而定义的基类
     *      被装饰者需要装饰很多类型的功能，该类就是负责管理这些具体的装饰类
     * 4， 具体装饰（ConcreteDecorator）：具体的装饰类
     *
     *
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

        //  抽象类中间再加一层父类，可以选择性的增强方法，不用都重写被装饰类父接口里的所有方法。
        Pepper pepper = new Pepper(sugarCoffee);
        System.out.println(pepper.money());
    }
























}
