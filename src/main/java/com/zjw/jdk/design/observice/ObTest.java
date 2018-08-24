package com.zjw.jdk.design.observice;

import org.junit.Test;

/**
 * Created by Administrator on 2018/8/24.
 */
public class ObTest {

    @Test
    public void test() {
        //观察者
        Observer observer1=new RealObserver("ob1");
        Observer observer2=new RealObserver("ob2");
        Observer observer3 = new RealObserver("ob3");

        //被观察者
        Subject subject = new RealSubject("周家伟驾到");
        subject.Attch(observer1);
        subject.Attch(observer2);
        subject.Attch(observer3);
        subject.notifyObserver();
        System.out.println("第二次-------------------------");
        subject.detach(observer1);
        subject.notifyObserver();
    }
}
