package com.pearz.JUC;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {

    private final static int num = 6;

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(num);

        for (int i = 0; i < num; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "离开");
            }, String.valueOf(i)).start();
            countDownLatch.countDown();

        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "离开");
        }, "班长").start();

    }
}
