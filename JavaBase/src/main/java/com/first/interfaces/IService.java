package com.first.interfaces;

/**
 *
 * @author luogeger
 */
public interface IService {



    static IService getImpl(String a) {
//        return new AppleImpl(a);
        return null;
    }


    void test ();


}
