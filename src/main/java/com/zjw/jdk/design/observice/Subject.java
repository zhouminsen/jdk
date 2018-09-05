package com.zjw.jdk.design.observice;

/**
 * Created by Administrator on 2018/8/24.
 * 被监控者
 */
public interface Subject {
    /**
     * 添加监控者
     */
    void Attch(Observer observer);

    /**
     * 删除监控者
     */
    void detach(Observer observer);

    /**
     * 通知监控者
     */
    void notifyObserver();
}
