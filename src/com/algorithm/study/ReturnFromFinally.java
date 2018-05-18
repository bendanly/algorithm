package com.algorithm.study;

import java.io.IOException;
/**
 * 1、finally中不能return，否则异常会被吞。且只返回finally中return的值
 * 2、finally中也不建议抛出异常，会覆盖正常异常
 * @author liyang
 *
 */
public class ReturnFromFinally {
	public static void main(String[] args) {
		  try {
		    doSomethingWhichThrowsException();
		    System.out.println("OK");   // incorrect "OK" message is printed
		  } catch (RuntimeException e) {
			  System.out.println(e.getMessage());  // this message is not shown
		  }
		}

		public static void doSomethingWhichThrowsException() {
			int q=0;
		  try {
		    throw new RuntimeException("测试异常信息1");
		  } finally {
		    for (int i = 0; i < 10; i ++) {
		      //...
		      if (q == i) {
		        break; // ignored
		      }
		    }

		    /* ... */
		   //return;      // Noncompliant - prevents the RuntimeException from being propagated		 
		   try{
			   throw new RuntimeException("测试异常信息2");
		   }catch (RuntimeException e) {			   
			
		}
		  }
		}
}
