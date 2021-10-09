package com.pearz.se;

public class ProductTest {
    public static void main(String[] args) {
        Clerk clerk = new Clerk();

        Producer producer = new Producer(clerk);
        Thread p1 = new Thread(producer);
        p1.setName("生产者1");

        Customer customer = new Customer(clerk);
        Thread c1 = new Thread(customer);
        Thread c2 = new Thread(customer);
        c1.setName("消费者1");
        c2.setName("消费者2");

        p1.start();
        c1.start();
//        c2.start();
    }
}

class Clerk {
    private int productNum = 0;

    public synchronized void addProduct() {
        if (productNum < 20) {
            ++productNum;
            notify();
            System.out.println(Thread.currentThread().getName() + "开始生产" + productNum + "号产品");
        } else {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void getProduct() {
        if (productNum > 0) {
            System.out.println(Thread.currentThread().getName() + "开始消费" + productNum + "号产品");
            --productNum;
            notify();
        } else {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Producer implements Runnable {
    private Clerk clerk;

    public Producer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.addProduct();
        }
    }
}

class Customer implements Runnable {
    private Clerk clerk;

    public Customer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.getProduct();
        }
    }
}