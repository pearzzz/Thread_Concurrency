package com.pearz.JUC;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();

        for (int i = 0; i < 5; i++) {
            String key = String.valueOf(i);
            new Thread(() -> {
                myCache.write(key, key);
            }, String.valueOf(i)).start();
        }
        for (int i = 0; i < 5; i++) {
            String key = String.valueOf(i);
            new Thread(() -> {
                myCache.read(key);
            }, String.valueOf(i)).start();
        }
    }
}

class MyCache {
    private volatile Map<String, Object> map = new HashMap<>();

    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void write(String key, Object value) {
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "开始写操作");
            Thread.sleep(300);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "写完了");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public void read(String key) {
        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "开始读操作");
            Thread.sleep(300);
            System.out.println(Thread.currentThread().getName() + "读完了" + map.get(key));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }
    }
}
