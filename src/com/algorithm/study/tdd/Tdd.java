package com.algorithm.study.tdd;

public class Tdd {
	public static final int UP_BOUND = 80;
	public static final int LOW_BOUND = 0;

	/**
	 * 
	 * @param number
	 *            between 0 and 8
	 * @return
	 * @throws Exception
	 */
	public static long fib(int number) throws InvalidInputException {

		if (LOW_BOUND > number || UP_BOUND < number) {
			throw new InvalidInputException();
		}

		if (0 == number || 1 == number) {
			return number;
		} else {
			return fib(number - 1) + fib(number - 2);
		}
	}
}
