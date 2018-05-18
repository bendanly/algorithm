package com.algorithm.study.ddd;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.algorithm.study.tdd.InvalidInputException;
import com.algorithm.study.tdd.Tdd;

public class StudentTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test(expected = InvalidInputException.class)
	public void input_0() throws InvalidInputException {
		System.out.print("intput 0:");
		Student.Say2(0);
	}
	@Test(expected = InvalidInputException.class)
	public void input_65536() throws InvalidInputException {
		System.out.print("intput 65536:");
		Student.Say2(65536);
	}
	
	@Test
	public void print_Fizz_input_3() {
		System.out.print("intput 3:");
		Student.Say2(3);
	}

	@Test
	public void print_Buzz_input_5() {
		System.out.print("intput 5:");
		Student.Say2(5);
	}
	@Test
	public void print_Whizz_input_7() {
		System.out.print("intput 7:");
		Student.Say2(7);
	}
	
	@Test
	public void print_FizzBuzz_input_15() {
		System.out.print("intput 15:");
		Student.Say2(15);
	}
	
	@Test
	public void print_FizzWhizz_input_21() {
		System.out.print("intput 21:");
		Student.Say2(21);
	}
	@Test
	public void print_BuzzWhizz_input_35() {
		System.out.print("intput 35:");
		Student.Say2(35);
	}
	@Test
	public void print_FizzBuzzWhizz_input_105() {
		System.out.print("intput 105:");
		Student.Say2(105);
	}
	@Test
	public void print_22_input_22() {
		System.out.print("intput 22:");
		Student.Say2(22);
	}
	@Test
	public void print_65535_input_65535() {
		System.out.print("intput 65535:");
		Student.Say2(65535);
	}
	@Test
	public void print_all() {
		for(int i=1;i<100;i++){
			System.out.print("intput "+i+":");
			Student.Say2(i);
		}
	}
}
