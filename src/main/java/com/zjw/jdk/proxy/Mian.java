package com.zjw.jdk.proxy;

import java.lang.reflect.*;

/**
 * Created by zhoum on 2019-06-27.
 */
public class Mian {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        //静态代理
        System.out.println("静态代理-----------------------------------");
        CalculatorImpl calculatorImpl = new CalculatorImpl();
        StaticProxy staticProxy = new StaticProxy(calculatorImpl);
        System.out.println(staticProxy.add(1, 2));
        System.out.println(staticProxy.subtract(1, 2));

        System.out.println("动态代理-----------------------------------");
        //动态代理
        Object o = getProxy();
        System.out.println(o);
        Calculator calculator = (Calculator) o;
        System.out.println(calculator.add(1, 2));
        System.out.println(calculator.subtract(1, 2));
        Calculator2 calculator2 = (Calculator2) o;
        System.out.println(calculator2.divide(4, 2));
        System.out.println(calculator2.multiply(4, 2));


        System.out.println("动态代理2-----------------------------------");
        //动态代理
        o = getProxy2(calculatorImpl);
        System.out.println(o);
        calculator = (Calculator) o;
        System.out.println(calculator.add(1, 2));
        System.out.println(calculator.subtract(1, 2));
        calculator2 = (Calculator2) o;
        System.out.println(calculator2.divide(4, 2));
        System.out.println(calculator2.multiply(4, 2));

        System.out.println("动态代理3-----------------------------------");
        Object proxy3 = getProxy3();
        calculator = (Calculator) proxy3;
        System.out.println(calculator.add(1, 2));
    }


    private static Object getProxy() {
        Object o = Proxy.newProxyInstance(CalculatorImpl.class.getClassLoader(), CalculatorImpl.class.getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println(method.getName() + " method to start");
                CalculatorImpl calculator = new CalculatorImpl();
                Object invoke = method.invoke(calculator, args);
                System.out.println(method.getName() + " method to end");
                return invoke;
            }
        });
        return o;
    }


    private static Object getProxy2(Object o) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> proxyClass = Proxy.getProxyClass(o.getClass().getClassLoader(), o.getClass().getInterfaces());
        Constructor<?> constructor = proxyClass.getConstructor(InvocationHandler.class);
        Object o1 = constructor.newInstance(new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println(method.getName() + " method to start");
                Object invoke = method.invoke(o, args);
                System.out.println(method.getName() + " method to end");
                return invoke;
            }
        });
        return o1;
    }

    private static Object getProxy3() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> proxyClass = Proxy.getProxyClass(Calculator.class.getClassLoader(), Calculator.class);
        Constructor<?> constructor = proxyClass.getConstructor(InvocationHandler.class);
        Object o = constructor.newInstance(new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return 1;
            }
        });
        return o;
    }
}
