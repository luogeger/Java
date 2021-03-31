package com.first.design.pattern.create.singleton;

import java.util.concurrent.TimeUnit;

/**
 * Double Check Lock
 *
 *
 */
public class DCL {

    private static DCL INSTANCE;

    public static DCL getInstance() {
        if (INSTANCE == null) {
            synchronized (DCL.class) {
                if (INSTANCE == null) {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    INSTANCE = new DCL();
                }
            }
        }

        return INSTANCE;
    }



}
