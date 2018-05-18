package com.algorithm.study.ddd;

import com.sun.javafx.image.IntPixelAccessor;

public class machine {
	public String run(Rule code, int number) {
		int[] a= new int[]{0,1};
		Rule r = code;
		while (r.isReduce()) {
			System.out.println(number);
			r = r.reuduce(number);
		}
		System.out.println(r.str(number));
		return r.getResult().res;
	}
}
