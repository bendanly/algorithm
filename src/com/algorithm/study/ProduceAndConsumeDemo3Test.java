package com.algorithm.study;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ProduceAndConsumeDemo3Test {
	private ProduceAndConsumeDemo3 produceAndConsumeDemo3 ;

	@Before
	public void setUp() throws Exception {
		produceAndConsumeDemo3= new ProduceAndConsumeDemo3();
	}

	@Test
	public void testMain() throws InterruptedException {
		produceAndConsumeDemo3.main(null);
	}

}
