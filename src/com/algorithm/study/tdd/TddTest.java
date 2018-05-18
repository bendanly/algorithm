package com.algorithm.study.tdd;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class TddTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void return_0_input_0_low_bound() throws InvalidInputException {
		assertEquals(0, Tdd.fib(0));
	}

	@Test
	public void return_1_input_1() throws InvalidInputException {
		assertEquals(1, Tdd.fib(1));
	}

	@Test
	public void return_ok_input_between_2_and_8() throws InvalidInputException {
		for (int i = 2; i <= 8; i++) {
			assertEquals(Tdd.fib(i - 1) + Tdd.fib(i - 2), Tdd.fib(i));
		}
	}

	@Test
	public void return_13_input_7() throws InvalidInputException {
		assertEquals(13, Tdd.fib(7));
	}

	@Test
	public void return_23416728348467685_input_80_up_bound() throws InvalidInputException {
		assertEquals(23416728348467685L, Tdd.fib(80));
	}

	@Test(expected = InvalidInputException.class)
	public void return_error_input_out_low_bound() throws InvalidInputException {
		Tdd.fib(-1);
	}
	
	@Test(expected = InvalidInputException.class)
	public void return_error_input_out_up_bound() throws InvalidInputException {
		Tdd.fib(81);
	}

}
