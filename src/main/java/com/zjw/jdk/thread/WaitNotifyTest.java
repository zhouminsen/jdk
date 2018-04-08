package com.zjw.jdk.thread;

import lombok.Data;

/**
 * Created by zhoum on 2018/4/4.
 */
public class WaitNotifyTest {
    public static void main(String[] args) throws InterruptedException {
        Account3 account2 = new Account3("周家伟", 0);
         new DrawThread3("取钱者", account2, 800).start();

//        Thread.sleep(1000);
        new DepositThread3("存款甲", account2, 800).start();
        new DepositThread3("存款乙", account2, 800).start();
        new DepositThread3("存款丙", account2, 800).start();
    }
}

@Data
class Account2 {
    private String accountNo;
    private double balance;

    private boolean flag = false;

    public Account2(String accountNo, double balance) {
        this.accountNo = accountNo;
        this.balance = balance;
    }


    public synchronized void draw(double drawAmount) {
        try {
            if (!flag) {
                wait();
            } else {
                System.out.println(Thread.currentThread().getName() + " 取钱:" + drawAmount);
                balance -= drawAmount;
                System.out.println("账户余额为:" + balance);
                flag = false;
                notifyAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void deposit(double depositAmount) {
        try {
            if (flag) {
                wait();
            } else {
                System.out.println(Thread.currentThread().getName() + " 存款:" + depositAmount);
                balance += depositAmount;
                System.out.println("账户余额为:" + balance);
                flag = true;
                notifyAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

class DrawThread2 extends Thread {
    private Account3 account;

    private double drawAmount;

    public DrawThread2(String name, Account3 account, double drawAmount) {
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
    private Account3 account;

    private double depositAmount;

    public DepositThread2(String name, Account3 account, double depositAmount) {
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