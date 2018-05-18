package com.algorithm.study.ddd;

import com.algorithm.study.tdd.InvalidInputException;

public class Student {
	public static final int MIN_BOUND = 0;
	public static final int MAX_BOUND  = 65535;
	public static int[] NUMBER = { 3, 5, 7 };

	public static void Say(int seq) {
		if (MIN_BOUND >= seq || MAX_BOUND < seq) {
			throw new InvalidInputException();
		}
		if (seq % NUMBER[0] == 0) {
			if (seq % NUMBER[1] == 0 && seq % NUMBER[2] == 0) {
				System.out.println("FlzzBlzzWhizz");
			} else if (seq % NUMBER[1] == 0) {
				System.out.println("FlzzBlzz");
			} else if (seq % NUMBER[2] == 0) {
				System.out.println("FlzzWhizz");
			} else {
				System.out.println("Flzz");
			}
		} else if (seq % NUMBER[1] == 0) {
			if (seq % NUMBER[2] == 0) {
				System.out.println("BlzzWhizz");
			} else {
				System.out.println("Blzz");
			}
		} else if (seq % NUMBER[2] == 0) {
			System.out.println("Whizz");
		} else {
			System.out.println(seq);
		}
	}

	public static void Say2(int seq) {
		if (MIN_BOUND >= seq|| MAX_BOUND < seq) {
			throw new InvalidInputException();
		}
		boolean isGet = false;

		if (String.valueOf(seq).contains(String.valueOf(NUMBER[0]))) {
			System.out.print("Flzz");
			isGet = true;
		} else {

			if (seq % NUMBER[0] == 0) {
				System.out.print("Flzz");
				isGet = true;
			}

			if (seq % NUMBER[1] == 0) {
				System.out.print("Blzz");
				isGet = true;
			}
			if (seq % NUMBER[2] == 0) {
				System.out.print("Whizz");
				isGet = true;
			}
		}
		if (!isGet) {
			System.out.print(seq);
		}
		System.out.println("");
	}
}
