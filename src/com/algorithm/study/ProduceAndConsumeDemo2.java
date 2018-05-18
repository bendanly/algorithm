package com.algorithm.study;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 生产/消费 并发模拟
 * 
 * @author liyang
 *
 */
public class ProduceAndConsumeDemo2 {

	private static List<String> productStore = new ArrayList<String>();

	public final static int MAX_PRODUCT = 10; // 仓储容量
	public final static int MIN_PRODUCT = 0; // 仓储下限

	private final static Lock lock = new ReentrantLock();
	private final static Condition full = lock.newCondition();
	private final static Condition empty = lock.newCondition();

	private static class Produce implements Runnable {

		@Override
		public void run() {

			lock.lock();
			try {
				Thread.sleep((long) (Math.random() * 100));
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			while (productStore.size() > MAX_PRODUCT) {// 如果大于容量
				try {
					System.out.println(Thread.currentThread().getName() + "报告：产品已满，稍候再生产。当前储量：" + productStore.size());
					full.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			productStore.add(String.valueOf(Math.random()));// 生产一个产品
			System.out.println(Thread.currentThread().getName() + "记录：生产一个产品["
					+ productStore.get(productStore.size() - 1) + "]，当前储量：" + productStore.size());
			empty.signal(); // 通知等待区的消费者可以取出产品了
			lock.unlock();
		}

	}

	private static class Consume implements Runnable {

		@Override
		public void run() {
			lock.lock();
			try {
				Thread.sleep((long) (Math.random() * 100));
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}

			while (productStore.size() <= ProduceAndConsumeDemo.MIN_PRODUCT) {
				try {
					System.out.println(Thread.currentThread().getName() + "报告：仓库缺货,稍候再取。当前储量：" + productStore.size());					
					empty.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			String t = productStore.get(0);
			productStore.remove(t);
			System.out.println(Thread.currentThread().getName() + "记录：消费一个产品[" + t + "]，当前储量：" + productStore.size());
			full.signal(); // 通知等待去的生产者可以生产产品了
			lock.unlock();
		}

	}

	public static void main(String[] args) throws InterruptedException {
		ThreadGroup group = new ThreadGroup("mygroup");	
		for (int i = 0; i < 100; i++) {
			Thread th1 = new Thread(group, new Produce(), "p->" + i);
			th1.start();
		}
		for (int i = 0; i < 100; i++) {
			Thread th2 = new Thread(group, new Consume(), "c->" + i);
			th2.start();
		}
		while (group.activeCount() != 0) {}
		System.out.println("结束，当前储量："+ProduceAndConsumeDemo2.productStore.size());		
	}

}
