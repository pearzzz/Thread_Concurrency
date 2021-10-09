package com.pearz.JUC;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {

    private final static int num = 7;

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(num, () -> {
            System.out.println("班长离开");
        });

        for (int i = 0; i < num; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "离开");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, i + "号同学").start();
        }


    }
}
