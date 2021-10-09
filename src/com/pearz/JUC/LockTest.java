package com.pearz.JUC;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {
    public static void main(String[] args) {
        LockDemo lockDemo = new LockDemo();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                lockDemo.print5();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                lockDemo.print10();
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                lockDemo.print15();
            }
        }, "C").start();
    }
}

class LockDemo {
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    private int tag = 1;

    public void print5() {
        lock.lock();
        try {
            while (tag != 1) {
                condition1.await();
            }
            for (int i = 0; i < 5; i++) {
                System.out.print(i + "    ");
            }
            System.out.println();
            tag = 2;
            condition2.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print10() {
        lock.lock();
        try {
            while (tag != 2) {
                condition2.await();
            }
            for (int i = 0; i < 10; i++) {
                System.out.print(i + "    ");
            }
            System.out.println();
            tag = 3;
            condition3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print15() {
        lock.lock();
        try {
            while (tag != 3) {
                condition3.await();
            }
            for (int i = 0; i < 15; i++) {
                System.out.print(i + "    ");
            }
            System.out.println();
            tag = 1;
            condition1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}