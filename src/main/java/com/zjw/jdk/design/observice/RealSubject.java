package com.zjw.jdk.design.observice;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/8/24.
 */
public class RealSubject implements Subject {

    private List<Observer> observerList = new ArrayList<>();

    private String message;

    public RealSubject(String message) {
        this.message = message;
    }

    @Override
    public void Attch(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observerList.remove(observer);
    }

    @Override
    public void notifyObserver() {
        for (Observer item : observerList) {
            item.update(message);
        }
    }
}
