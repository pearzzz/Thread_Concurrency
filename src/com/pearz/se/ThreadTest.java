package com.pearz.se;

public class ThreadTest {
    public static void main(String[] args) {
        MyThread myThread = new MyThread("线程");
//        myThread.setName("线程1:");

        myThread.start();
        myThread.setPriority(Thread.MAX_PRIORITY);

        Thread.currentThread().setName("mmmmain");
        for (int i = 0; i < 10000; i++) {
            if (i % 2 != 0) {
                System.out.println(Thread.currentThread().getName() + " " + Thread.currentThread().getPriority() + " " + i);
            }

            if (i == 199) {
                try {
                    myThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

class MyThread extends Thread {
    public MyThread() {}

    public MyThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            if (i % 2 == 0) {
                System.out.println(getName() + " " + getPriority() + " " + i);
//                try {
//                    sleep(100);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
            if (i % 20 == 0) {
                this.yield();
            }
        }
    }
}