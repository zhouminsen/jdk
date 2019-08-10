package com.zjw.jdk.thread;

import lombok.Data;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zhoum on 2018/4/4.
 */
public class LockConditionTest {
    public static void main(String[] args) throws InterruptedException {
        Account3 account3 = new Account3("周家伟", 0);
        new DrawThread3("取钱者", account3, 800).start();

//        Thread.sleep(1000);
        new DepositThread3("存款甲", account3, 800).start();
        new DepositThread3("存款乙", account3, 800).start();
        new DepositThread3("存款丙", account3, 800).start();
    }

    @Data
    public static class Account3 {
        private String accountNo;
        private double balance;

        private boolean flag = false;

        public Account3(String accountNo, double balance) {
            this.accountNo = accountNo;
            this.balance = balance;
        }

        private Lock lock = new ReentrantLock();

        private Condition condition = lock.newCondition();

        public  void draw(double drawAmount) {
            try {
                lock.lock();
                if (!flag) {
                    condition.await();
                } else {
                    System.out.println(Thread.currentThread().getName() + " 取钱:" + drawAmount);
                    balance -= drawAmount;
                    System.out.println("账户余额为:" + balance);
                    flag = false;
                    condition.signalAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }

        public synchronized void deposit(double depositAmount) {
            try {
                lock.lock();
                if (flag) {
                    condition.await();
                } else {
                    System.out.println(Thread.currentThread().getName() + " 存款:" + depositAmount);
                    balance += depositAmount;
                    System.out.println("账户余额为:" + balance);
                    flag = true;
                    condition.signalAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }

    }

    static class DrawThread3 extends Thread {
        private Account3 account;

        private double drawAmount;

        public DrawThread3(String name, Account3 account, double drawAmount) {
            this.account = account;
            this.drawAmount = drawAmount;
            super.setName(name);
        }


        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                account.draw(drawAmount);
            }
        }
    }

    static class DepositThread3 extends Thread {
        private Account3 account;

        private double depositAmount;

        public DepositThread3(String name, Account3 account, double depositAmount) {
            this.account = account;
            this.depositAmount = depositAmount;
            super.setName(name);
        }


        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                account.deposit(depositAmount);
            }
        }
    }
}






