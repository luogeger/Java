package com.first.abstractKeyWord.b;

import lombok.Data;

/**
 * @author luoxiaoqing
 * @date 2020-02-06__03:06
 */
@Data
public abstract class MyAbstract implements MyInterface {

    private String music = "Role";

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
