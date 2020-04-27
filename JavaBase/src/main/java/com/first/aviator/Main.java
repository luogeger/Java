package com.first.aviator;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;
import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorDouble;
import com.googlecode.aviator.runtime.type.AviatorObject;
import org.junit.Test;

import java.util.*;

/**
 * @author luoxiaoqing
 */
public class Main {


    /**
     * Aviator的数值类型仅支持Long和Double, 任何整数都将转换成Long, 任何浮点数都将转换为Double, 包括用户传入的变量数值
     */
    @Test
    public void main01() {
        Long result = (Long) AviatorEvaluator.execute("1+2+3");
        System.out.println(result);
        System.out.println(result.getClass().toString());

        // 报错： java.lang.ClassCastException: java.lang.Long cannot be cast to java.lang.Integer
        Integer result1 = (Integer) AviatorEvaluator.execute("1+2+3");
        System.out.println(result1);
        System.out.println(result1.getClass().toString());
    }


    /**
     * 使用变量
     * <p>让Aviator负责字符串的相加:</p>
     */
    @Test
    public void main02() {
        String key = "name";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("hello", "你好！ ");
        map.put(key, "Michael..");
        // hello 和 name  是map里的key
        String result = (String) AviatorEvaluator.execute(" ''+hello  + name ", map);
        System.out.println(result);  // 你好！ Michael..
        System.out.println(result.getClass().toString());
    }

    /**
     * 转义符
     * <p>" \"a\'b\" ": 转换单引号</p>
     */
    @Test
    public void main03() {
        Object result1 = AviatorEvaluator.execute(" 'a\"b' ");           // 字符串 a"b
        System.out.println(result1);
        Object result2 = AviatorEvaluator.execute(" \"a\'b\" ");         // 字符串 a'b
        System.out.println(result2);
        Object result3 = AviatorEvaluator.execute(" 'hello ' + 3 ");     // 字符串 hello 3
        System.out.println(result3);
        String result4 = (String) AviatorEvaluator.execute(" 'hello '+ unknow "); // 字符串 hello null
        System.out.println(result4);

    }


    /**
     * exec方法
     * <p>不需要构造Map</p>
     */
    @Test
    public void main04() {
        String name = "luoxiaoqing";
        int age = 18;
        Object result = AviatorEvaluator.exec(" 'hello ' + aaa + age ", name, age);// hello dennis
        System.out.println(result);
        System.out.println(result.getClass().toString());
    }


    /**
     * 函数调用
     * <p>string.length 的s必须是小写</p>
     */
    @Test
    public void main05() {
        Object execute = AviatorEvaluator.execute("string.length('hello')");
        System.out.println(execute);

        Object result = AviatorEvaluator.execute("string.contains(\"test\", string.substring('hello', 1, 2))");
        System.out.println(result);
        System.out.println(result.getClass().toString());
    }


    /**
     * 自定义函数
     * <p>注册函数通过AviatorEvaluator.addFunction方法, 移除可以通过removeFunction</p>
     */
    @Test
    public void main06() {
        AviatorEvaluator.addFunction(new AddFunction());
        System.out.println(AviatorEvaluator.execute("add(1, 2)"));           // 3.0
        System.out.println(AviatorEvaluator.execute("add(add(1, 2), 100)")); // 103.0

    }

    /**
     * 编译表达式
     * <p></p>
     */
    @Test
    public void main07() {
//        String expression = "a-(b-c)>100";
        String expression = "a+b+c";
        // 编译表达式
        Expression compiledExp = AviatorEvaluator.compile(expression);
        Map<String, Object> env = new HashMap<>(3);
        env.put("a", 100.3);
        env.put("b", 45);
        env.put("c", -199.100);
        // 执行表达式
//        Boolean result = (Boolean) compiledExp.execute(env);
//        System.out.println(result);

        // 只能转换成Double，不然报错：java.lang.Double cannot be cast to java.lang.Integer
        Double result = (Double) compiledExp.execute(env);
        System.out.println(result);
    }


    /**
     * 访问数组和集合
     * <p>可以通过中括号去访问数组和java.util.List对象, 可以通过map.key访问java.util.Map中key对应的value</p>
     */
    @Test
    public void main08() {
        final List<String> list = new ArrayList<>();
        list.add("hello");
        list.add(" world");

        final int[] array = new int[3];
        array[0] = 0;
        array[1] = 1;
        array[2] = 3;

        final Map<String, Date> map = new HashMap<>();
        map.put("date", new Date());

        Map<String, Object> env = new HashMap<>();
        env.put("list", list);
        env.put("array", array);
        env.put("mmap", map);

        System.out.println(AviatorEvaluator.execute("list[0]+list[1]", env));   // hello world
        System.out.println(AviatorEvaluator.execute("'array[0]+array[1]+array[2]=' + (array[0]+array[1]+array[2])", env));  // array[0]+array[1]+array[2]=4
        System.out.println(AviatorEvaluator.execute("'today is ' + mmap.date ", env));  // today is Wed Feb 24 17:31:45 CST 2016
    }


    /**
     * 三元操作符
     */
    @Test
    public void main09() {
        // tips: 这里接收的是String， 不是Boolean
        String boo = (String) AviatorEvaluator.exec("a>0? 'yes':'no'", 1);
        System.out.println(boo);
    }


    /**
     * 正则表达式匹配
     * <p>支持类 Ruby 和 Perl 风格的表达式匹配运算,通过=~操作符, 如下面这个例子匹配 email 并提取用户名返回:</p>
     */
    @Test
    public void main10() {
        String email = "luogeger@gmail.com";


        Map<String, Object> env = new HashMap<>();
        String key = "cc";
        env.put(key, email);

        // 字符串里的cc必须是key的真实值，不能用变量名(key)代替
        String username = (String) AviatorEvaluator.execute("cc =~ /([\\w0-8]+)@\\w+[\\.\\w+]+/ ? $1 : 'unknow' ", env);
        System.out.println(username);
    }


    /**
     * 变量的语法糖
     */
    @Test
    public void main11() {
        TestAviator foo = new TestAviator(100, 3.14f, new Date());
        Map<String, Object> env = new HashMap<>();
        env.put("foo", foo);
        System.out.println(AviatorEvaluator.execute("'foo.i = '+foo.i", env));   // foo.i = 100
        System.out.println(AviatorEvaluator.execute("'foo.f = '+foo.f", env));   // foo.f = 3.14
        System.out.println(AviatorEvaluator.execute("'foo.date.year = '+(foo.date.year+1990)", env));  // foo.date.year = 2106
    }


}


/**
 * 自定义函数
 */
class AddFunction extends AbstractFunction {
    @Override
    public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2) {
        Number left = FunctionUtils.getNumberValue(arg1, env);
        Number right = FunctionUtils.getNumberValue(arg2, env);
        return new AviatorDouble(left.doubleValue() + right.doubleValue());
    }


    @Override
    public String getName() {
        return "add";
    }
}


class TestAviator {

    private int i;
    private float f;
    private Date date;

    public TestAviator(int i, float f, Date date) {
        this.i = i;
        this.f = f;
        this.date = date;
    }
}