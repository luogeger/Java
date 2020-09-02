package com.first.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 用于Demo的锁对象
 * <p>一个锁对象能不能给多个资源当锁</p>
 */
public class DemoLock {
    public static final ReentrantReadWriteLock rrwLock = new ReentrantReadWriteLock();
    public static final ReentrantReadWriteLock.ReadLock READ_LOCK = rrwLock.readLock();
    public static final ReentrantReadWriteLock.WriteLock WRITE_LOCK = rrwLock.writeLock();
}
