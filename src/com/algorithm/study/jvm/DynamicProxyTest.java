package com.algorithm.study.jvm;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by liyang on 2018/1/6.
 * 传统的动态代理类实现方式，通过实现InvocationHandler接口来完成
 */
public class DynamicProxyTest {
    interface IHello {
        void sayHello();
    }
    static class MyHello implements IHello {

        public void sayHello() {
            System.out.println("hello word!");
        }
    }
    static class DynamicProxyHello implements InvocationHandler {
        Object obj;
        Object bind(Object obj) {
            this.obj = obj;
            return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), this);
        }
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("im a proxy");
            Object object= method.invoke(obj,args);
            System.out.println("proxy end");
            return object;
        }
    }
    public static  void main(String[] args){
        IHello hello = (IHello) new DynamicProxyHello().bind(new MyHello());
        hello.sayHello();
    }
}
