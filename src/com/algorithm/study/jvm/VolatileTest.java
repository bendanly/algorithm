package com.algorithm.study.jvm;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by liyang on 2018/3/23
 * <p>
 * volatile 关键词主要有两个特性
 * 1、线程间变量可见，修改变量的值，会立即刷新主内存，且将其他线程缓存中的置为无效，重新从主内存读取
 * 2、禁止进行指令重排序
 * <p>
 * 但是不能保障并发的原子性，下面例子里结果不一定是10000
 */
public class VolatileTest {
    public volatile int inc = 0;

    public void increase() {
        inc++;
    }

    public static void main(String[] args) {
        final VolatileTest test = new VolatileTest();
        for (int i = 0; i < 10; i++) {
            new Thread() {
                public void run() {
                    for (int j = 0; j < 1000; j++)
                        test.increase();
                }
            }.start();
        }

        while (Thread.activeCount() > 1) {  //保证前面的线程都执行完
            Thread.yield();
        }
        System.out.println(test.inc);

    }
}
