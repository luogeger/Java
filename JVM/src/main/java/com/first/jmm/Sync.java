package com.first.jmm;

/**
 *
 */
public class Sync {
    synchronized void fn () {

    }

    void fn1 () {
        synchronized (this) {

        }
    }
}
