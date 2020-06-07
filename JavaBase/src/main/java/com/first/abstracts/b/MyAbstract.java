package com.first.abstracts.b;

import lombok.Data;

/**
 * @author luoxiaoqing
 * @date 2020-02-06__03:06
 */
@Data
public abstract class MyAbstract implements MyInterface {

    private String tempFiled = "Role";


    public MyAbstract(String tempFiled) {
        this.tempFiled = tempFiled;
    }

    public abstract void play();

    protected abstract void happy();

    public void sing(){
        System.out.println("sing");
    }

    @Override
    public void eat() {

    }

    @Override
    public void drink() {

    }
}
