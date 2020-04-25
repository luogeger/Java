package com.first.aviator;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;
import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorDouble;
import com.googlecode.aviator.runtime.type.AviatorObject;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author luoxiaoqing
 */
public class Main {


    /**
     * Aviator的数值类型仅支持Long和Double, 任何整数都将转换成Long, 任何浮点数都将转换为Double, 包括用户传入的变量数值
     */
    @Test
    public void main1 () {
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
    public void main2 () {
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
    public void main3 () {
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
    public void main4 () {
        String name = "dennis";
        int age = 18;
        Object result = AviatorEvaluator.exec(" 'hello ' + yourName + age ", name, age);// hello dennis
        System.out.println(result);
        System.out.println(result.getClass().toString());
    }


    /**
     * 函数调用
     * <p>string.length 的s必须是小写</p>
     */
    @Test
    public void main5 () {
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
    public void main6 () {
        AviatorEvaluator.addFunction(new AddFunction());
        System.out.println(AviatorEvaluator.execute("add(1, 2)"));           // 3.0
        System.out.println(AviatorEvaluator.execute("add(add(1, 2), 100)")); // 103.0

    }

    /**
     * 编译表达式
     * <p></p>
     */
    @Test
    public void main7 () {
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