package com.pearz.se;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadByCallable {
    public static void main(String[] args) {
        CallableThread ct = new CallableThread();

        FutureTask futureTask = new FutureTask<Object>(ct);

        Thread thread = new Thread(futureTask);

        thread.start();

        try {
            Object o = futureTask.get();
            System.out.println("------" + o);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}

class CallableThread implements Callable {


    @Override
    public Object call() throws Exception {
        int sum = 0;
        for (int i = 0; i <= 100; i++) {
            if (i % 2 == 0) {
                System.out.println(i);
                sum += i;
            }
        }
        return sum;
    }


}
