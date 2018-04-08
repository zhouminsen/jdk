package com.zjw.jdk.thread;

import lombok.Data;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zhoum on 2018/4/3.
 */
public class ReentrantLockTest {

    public static void main(String[] args) throws InterruptedException {
        Account3 account = new Account3("周家伟", 1000);
        DrawThread3 drawThread = new DrawThread3("甲", account, 800);
        drawThread.start();
//        Thread.sleep(1000);
        DrawThread3 drawThread2 = new DrawThread3("乙", account, 800);
        drawThread2.start();
    }
}
@Data
class DrawThread extends Thread {
    private Account3 account;

    private double drawAmount;

    public DrawThread(String name, Account3 account, double drawAmount) {
        this.account = account;
        this.drawAmount=drawAmount;
        super.setName(name);
    }


    @Override
    public void run() {
        account.draw(drawAmount);
    }
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
