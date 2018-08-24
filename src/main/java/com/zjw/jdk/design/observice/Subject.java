package com.zjw.jdk.design.observice;

/**
 * Created by Administrator on 2018/8/24.
 */
public interface Subject {
    void Attch(Observer observer);

    void detach(Observer observer);

    void notifyObserver();
}
