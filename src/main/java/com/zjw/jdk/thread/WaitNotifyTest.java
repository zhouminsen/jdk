package com.zjw.jdk.thread;

import lombok.Data;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zhoum on 2018/4/4.
 */
public class WaitNotifyTest {
    public static void main(String[] args) throws InterruptedException {
        LockConditionTest.Account3 account2 = new LockConditionTest.Account3("周家伟", 0);
         new LockConditionTest.DrawThread3("取钱者", account2, 800).start();

//        Thread.sleep(1000);
        new LockConditionTest.DepositThread3("存款甲", account2, 800).start();
        new LockConditionTest.DepositThread3("存款乙", account2, 800).start();
        new LockConditionTest.DepositThread3("存款丙", account2, 800).start();
    }

    @Data
    class Account {
        private String accountNo;
        private double balance;

        public Account(String accountNo, double balance) {
            this.accountNo = accountNo;
            this.balance = balance;
        }

        ReentrantLock reentrantLock = new ReentrantLock();

        public void draw(double drawAmount) {
            reentrantLock.lock();
            try {
                if (balance >= drawAmount) {

                    System.out.println(Thread.currentThread().getName() + "取钱成功!吐出钞票" + drawAmount);
                    balance -= drawAmount;

            /*    try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/

                    System.out.println("余额为:" + balance);
                } else {
                    System.out.println(Thread.currentThread().getName() + "取钱失败!余额不足" + drawAmount);
                }
            } finally {
                reentrantLock.unlock();
            }
        }

    }

    class DrawThread2 extends Thread {
        private LockConditionTest.Account3 account;

        private double drawAmount;

        public DrawThread2(String name, LockConditionTest.Account3 account, double drawAmount) {
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

    class DepositThread2 extends Thread {
        private LockConditionTest.Account3 account;

        private double depositAmount;

        public DepositThread2(String name, LockConditionTest.Account3 account, double depositAmount) {
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





