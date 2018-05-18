package com.algorithm.study;

import java.io.FileInputStream;

/**
 * try catch finally 执行顺序
 * 
 * @author liyang
 *
 */
public class FinallyExcuteOrder {
	public static void main(String[] args) {
		FinallyExcuteOrder f = new FinallyExcuteOrder();
		System.out.println(f.amethod());
	}

	public String amethod() {
		try {
			// 1，抛出异常
			FileInputStream dis = new FileInputStream("test1.txt"); // 目录不存在，抛出异常

		} catch (Exception ex) {
			// 2.catch捕获异常，并执行
			System.out.println("No such file found");
			// 4,return 返回
			return "normal return";
		} finally {
			// 3.finally一定会在return之前执行。（准确说，应该是return;语句）
			System.out.println("Done finally");
		}
		return "return after finally";
	}
}
