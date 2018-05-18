package com.algorithm.study;

import java.util.HashMap;
import java.util.Map;

public class StackoverflowTest {
	int stackLength = 0;

	public void stackoverfunction() {
		stackLength++;
		stackoverfunction();

	}
	
	
	public static void main(String[] args) throws Throwable {
		StackoverflowTest test = new StackoverflowTest();
		try {
			test.stackoverfunction();
		} catch (Throwable e) {
			System.out.println(test.stackLength);
			throw e;
		}

	}
}
