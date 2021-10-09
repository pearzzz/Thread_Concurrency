package com.pearz.JUC;

public class InDecreaseBySynchronized {
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

class DemoClass {
    private int number = 0;

    public synchronized void increase() {
        while (number != 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        ++number;
        System.out.println(Thread.currentThread().getName() + "执行+1操作， 当前number = " + number);
        notifyAll();
    }

    public synchronized void decrease() {
        while (number == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        --number;
        System.out.println(Thread.currentThread().getName() + "执行-1操作， 当前number = " + number);
        notifyAll();
    }
}
