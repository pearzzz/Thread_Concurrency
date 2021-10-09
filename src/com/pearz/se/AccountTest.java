package com.pearz.se;

import java.util.concurrent.locks.ReentrantLock;

public class AccountTest {
    public static void main(String[] args) {
        Account account = new Account(0);
        Customer0 customer1 = new Customer0(account);
        Customer0 customer2 = new Customer0(account);

        customer1.setName("客户一");
        customer2.setName("客户二");

        customer1.start();
        customer2.start();
    }
}

class Account {
    private double balance = 0;
    private ReentrantLock lock = new ReentrantLock();

    public Account(double balance) {
        this.balance = balance;
    }

    public void deposit(double amt) {

        try {
            lock.lock();
            if (amt > 0) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                balance += amt;
                System.out.println(Thread.currentThread().getName() + "存钱成功，余额为：" + balance);
            }
        } finally {
            lock.unlock();
        }


    }
}

class Customer0 extends Thread {
    private Account account;

    public Customer0(Account account) {
        this.account = account;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            account.deposit(1000);
        }
    }
}
