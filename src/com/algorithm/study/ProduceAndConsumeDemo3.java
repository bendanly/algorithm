package com.algorithm.study;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class ProduceAndConsumeDemo3 {
	private static BlockingQueue<Object> productStore = new  LinkedBlockingQueue<Object>(100);

	public final static int MAX_PRODUCT = 50; // 仓储容量
	public final static int MIN_PRODUCT = 0; // 仓储下限

	private static class Produce implements Runnable {

		@Override
		public void run() {

			try {
				Thread.sleep((long) (Math.random() * 100));
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}

			if(productStore.size()>MAX_PRODUCT){				
				System.out.println(Thread.currentThread().getName() + "报告：产品已满，稍候再生产。当前储量：" + productStore.size());					
			}
			Object t = Math.random();
			try {
				productStore.put(t);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}// 生产一个产品
			System.out.println(Thread.currentThread().getName() + "记录：生产一个产品["
					+t + "]，当前储量：" + productStore.size());
			
		}

	}

	private static class Consume implements Runnable {

		@Override
		public void run() {
		
			try {
				Thread.sleep((long) (Math.random() * 100));
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			Object t = null;
			if (productStore.size() <= ProduceAndConsumeDemo.MIN_PRODUCT) {
				System.out.println(Thread.currentThread().getName() + "报告：仓库缺货,稍候再取。当前储量：" + productStore.size());
			}
				try {					
					t = productStore.take();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			System.out.println(Thread.currentThread().getName() + "记录：消费一个产品[" + t + "]，当前储量：" + productStore.size());
			
		}

	}

	public static void main(String[] args) throws InterruptedException {
		ThreadGroup group = new ThreadGroup("mygroup");
		System.out.println("start");
		ProduceAndConsumeDemo t2 = new ProduceAndConsumeDemo();
		System.out.println(Math.random());
		for (int i = 0; i < 100; i++) {
			Thread th1 = new Thread(group, new Produce(), "p->" + i);
			th1.start();
		}
		for (int i = 0; i < 100; i++) {
			Thread th2 = new Thread(group, new Consume(), "c->" + i);
			th2.start();

		}

		while (group.activeCount() > 0) {
			Thread.sleep(10);
		}
		System.out.println("test");
	}
}
