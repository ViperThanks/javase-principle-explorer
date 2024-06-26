package com.yiren.principle.juc.Sync原理;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * desc:
 *
 * @author Viper Thanks
 * @since 16/4/2024
 */
public class SyncTest {
    static final Object lock = new Object();
    static int count = 0;

    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition1 = reentrantLock.newCondition();
        synchronized (lock) {
            count++;
        }
    }
}
