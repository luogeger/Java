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

    private Integer result;

    public Integer getResponse() {
        return response;
    }

    public void setResponse(Integer response) {
        this.response = response;
        this.result = response -10;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }
}
