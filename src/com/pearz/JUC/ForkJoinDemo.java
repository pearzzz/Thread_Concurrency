package com.pearz.JUC;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ForkJoinDemo {
    public static void main(String[] args) {
        MyThread myThread = new MyThread(0, 100);

        ForkJoinPool forkJoinPool = new ForkJoinPool();

        ForkJoinTask<Integer> result = forkJoinPool.submit(myThread);

        try {
            System.out.println(result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            forkJoinPool.shutdown();
        }
    }
}

class MyThread extends RecursiveTask<Integer> {

    private int start;
    private int end;
    private Integer sum = 0;

    public MyThread(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        if (end - start <= 10) {
            for (int i = start; i <= end; ++i) {
                sum += i;
            }
        } else {
            int mid = (start + end) / 2;
            MyThread myThread1 = new MyThread(start, mid);
            MyThread myThread2 = new MyThread(mid + 1, end);
            myThread1.fork();
            myThread2.fork();
            sum = myThread1.join() + myThread2.join();
        }

        return sum;
    }
}
