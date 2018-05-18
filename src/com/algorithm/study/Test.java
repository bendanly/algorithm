package com.algorithm.study;

import java.util.HashMap;
import java.util.UUID;

public class Test {
	public Test(){
		doSome();//父类构造函数调用父类中方法doSome时，如果doSome没有声明为final，则有可能会被子类覆盖doSome，导致父类构造函数原定执行的代码逻辑改变
	}
	public void doSome(){
		 System.out.println("this is test");
	 }
	void func(int n, int m, int[] a, final int M) {
		int i = n, j;
		for (i = n; i >= m; i--) {
			a[m] = i;
			if (m > 1) {
				func(i - 1, m - 1, a, M);
			} else {
				for (j = M; j > 0; j--)
					System.out.print(a[j]);
				System.out.println("");
			}
		}
	}

	public String test2() {
		String t1 = "OK";
		String t2 = "ERROR";
		try {
			return t1;
		} catch (Exception ex) {
		} finally {
			System.out.println("haha");
		}
		return t2;
	}
	public void testf(int a) {
		try {
			int i = 0 / a;
		} catch (Exception ex) {
			System.err.println("程序异常了!");
		} finally {
			System.out.println("执行finally!");
		}
		System.out.println("执行finally后面的语句!");
	}

	/*
	 * public static void main(String[] args){
	 * 
	 * String tString = "   testet   "; tString.trim();
	 * System.out.println(tString); test t= new test(); int[] a = new int[10];
	 * //t.func(10, 4, a, 4);
	 * 
	 * 
	 * double f = 0.1d; float k = 0.1f; for(int i=0;i<9;i++){ f+=0.1; k+=0.1; }
	 * 
	 * System.out.println(f); if(f==1d){ System.out.println("OK"); }else{
	 * System.out.println("ERROR"); } t.test2(); }
	 */
	public static void main(String[] args) {
		final HashMap<String, String> map = new HashMap<String, String>(2);
		for (int i = 0; i < 10000; i++) {
		    new Thread(new Runnable() {
		        @Override
		        public void run() {
		            map.put(UUID.randomUUID().toString(), "");
		            System.out.println(Thread.currentThread().getName());
		        }
		    }).start();
		}		
	}
}
