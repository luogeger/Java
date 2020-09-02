package com.first.lock;

/**
 *
 */
public class ReadWriteLock {

//    private static final ReentrantReadWriteLock rrwLock = new ReentrantReadWriteLock();
//    private static final ReentrantReadWriteLock.ReadLock readLock = rrwLock.readLock();
//    private static final ReentrantReadWriteLock.WriteLock writeLock = rrwLock.writeLock();

    public static void main(String[] args) {
        new Thread(ReadWriteLock::read).start();// 0
        new Thread(ReadWriteLock::write).start();// 1
        new Thread(ReadWriteLock::read).start();// 2-
        new Thread(ReadWriteLock::read).start();// 3-
        new Thread(ReadWriteLock::read).start();// 4-
        new Thread(ReadWriteLock::read).start();// 5-
        new Thread(ReadWriteLock::write).start();// 6
        new Thread(ReadWriteLock::write).start();// 7
        new Thread(ReadWriteLock::write).start();// 8
        new Thread(ReadWriteLock::write).start();// 9
    }

    private static void write() {
        String name = Thread.currentThread().getName();
        DemoLock.WRITE_LOCK.lock();

        try {
            System.out.println(name + " acquire write lock");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(name + " release write lock...");
            DemoLock.WRITE_LOCK.unlock();
        }
    }

    private static void read() {
        String name = Thread.currentThread().getName();
        DemoLock.READ_LOCK.lock();

        try {
            System.out.println(name + " acquire read lock");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(name + " release read lock ...");
            DemoLock.READ_LOCK.unlock();
        }
    }
}

/*

Thread-6 acquire write lock
Thread-6 release write lock...
Thread-9 acquire write lock
Thread-9 release write lock...
Thread-7 acquire write lock
Thread-7 release write lock...
Thread-8 acquire write lock
Thread-8 release write lock...
Thread-1 acquire write lock
Thread-1 release write lock...
Thread-3 acquire read lock
Thread-4 acquire read lock
Thread-2 acquire read lock
Thread-5 acquire read lock
Thread-0 acquire read lock
Thread-5 release read lock ...
Thread-3 release read lock ...
Thread-4 release read lock ...
Thread-2 release read lock ...
Thread-0 release read lock ...

*/


















