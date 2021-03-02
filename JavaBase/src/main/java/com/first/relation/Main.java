package com.first.relation;

import org.junit.Test;

public class Main {

    /**
     * 执行顺序
     * <p>
     * 1. 静态成员变量
     * 2. 静态代码块
     * 3. 成员变量
     * 4. 构造器
     */
    @Test
    public void main1() {
        Person person = new Person();

    }
}
