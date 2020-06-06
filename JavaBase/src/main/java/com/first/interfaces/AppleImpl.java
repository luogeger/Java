package com.first.interfaces;

/**
 *
 * @author
 */
public class AppleImpl implements IService {

    public static final int AGE = 19;


    private String  name;

    public AppleImpl(String str) {
        this.name = str;
    }

    @Override
    public void test() {
        System.out.println(name);
    }
}
