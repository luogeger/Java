package com.first.constructor;

import org.junit.Test;

/**
 * @author luoxiaoqing
 */
public class ConstructorTest {
    @Test
    public void main1 () {
        A a = new A();
        a.setResponse(100);
        System.out.println(a.getResult());
    }
}

class A {

    private Integer response;
    /**
     * 只在本类使用，只提供get方法
     */
    private Integer result;

    public Integer getResponse() {
        return response;
    }

    /**
     * set赋值的时候，也给另外一个属性赋值
     * @param response
     */
    public void setResponse(Integer response) {
        this.response = response;
        this.result = response -10;
    }

    public Integer getResult() {
        return result;
    }

}
