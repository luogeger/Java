package com.first.designPattern.behavior.strategy.c;

/**
 * @author luoxiaoqing
 * @date 2020-01-11__14:27
 */
public class App {

    public static void main(String[] args) {
        Context numericValidator = new Context(new ValidationStrategy() {
            @Override
            public boolean validate(String str) {
                    return str.matches("[a-z]+");
            }
        });
        boolean b1 = numericValidator.execute("aaa");
        System.out.println(b1);


        Context lowerCareValidator = new Context(s -> s.matches("\\d+"));
        boolean b2 = lowerCareValidator.execute("aaa");
        System.out.println(b2);


    }
}
