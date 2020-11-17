package com.first.interfaces;

import org.junit.Test;

/**
 *
 * @author
 */
public class Main {

    @Test
    public void main1 () {
        IService impl = IService.getImpl("A");
        impl.test();
    }
}
