package com.pearz.JUC;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class InDecreaseByLock {
    public static void main(String[] args) {
        DemoClass demoClass = new DemoClass();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                demoClass.increase();
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                demoClass.decrease();
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                demoClass.increase();
            }
        }, "C").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                demoClass.decrease();
            }
        }, "D").start();
    }
}

class DemoClass1 {
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increase() {
        lock.lock();
        try {
            while (number != 0) {
                condition.await();
            }
            ++number;
            System.out.println(Thread.currentThread().getName() + "执行+1操作， 当前number = " + number);
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void decrease() {
        lock.lock();
        try {
            while (number == 0) {
                condition.await();
            }
            --number;
            System.out.println(Thread.currentThread().getName() + "执行-1操作， 当前number = " + number);
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}