package com.zjw.jdk.thread;

/**
 * Created by zhoum on 2018/4/8.
 */
public class ThreadLocalAccount {
    //定义一个ThreadLocal类型的变量,该变量将是一个线程局部变量,每个线程都会保留该变量的一个副本
    private ThreadLocal<String> name = new ThreadLocal<>();

    public ThreadLocalAccount(String name) {
        this.name.set(name);
        System.out.println();
    }

    public String getName() {
        return name.get();
    }

public void setName(String name) {
        this.name.set(name);
    }


    static class ThreadLocalAccountThread extends Thread {

        private ThreadLocalAccount account;

        public ThreadLocalAccountThread(ThreadLocalAccount account, String name) {
            super(name);
            this.account = account;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                if (i == 6) {
                    account.setName(getName());
                }
                System.out.println(account.getName() + " 账户的i值: " + i);
            }
        }

        public static void main(String[] args) {
            ThreadLocalAccount at = new ThreadLocalAccount("初始名");
            new ThreadLocalAccountThread(at, "线程甲").start();
            new ThreadLocalAccountThread(at, "线程乙").start();
        }
    }
}



