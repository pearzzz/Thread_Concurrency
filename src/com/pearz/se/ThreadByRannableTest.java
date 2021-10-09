package com.pearz.se;

public class ThreadByRannableTest {
    public static void main(String[] args) {
        MThread mThread = new MThread();

        Thread t1 = new Thread(mThread);
        Thread t2 = new Thread(mThread);
        Thread t3 = new Thread(mThread);

        t1.setName("窗口一");
        t2.setName("窗口二");
        t3.setName("窗口三");

        t1.start();
        t2.start();
        t3.start();
    }
}

class MThread implements Runnable {
    private int ticket = 1;

//    Object obj = new Object();

    @Override
    public void run() {

        while (ticket <= 100) {
            synchronized (this) {
                if (ticket <= 100) {
                    System.out.println(Thread.currentThread().getName() + "：卖出了票号" + ticket);
                    ++ticket;
                }
            }
        }
    }
}
