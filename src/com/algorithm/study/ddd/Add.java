package com.algorithm.study.ddd;

public class Add extends Rule {
	private Rule left;
	private Rule right;

	public Add() {
		// TODO Auto-generated constructor stub
	}

	public Add(Rule left, Rule right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public boolean isReduce() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Result getResult() {
		// TODO Auto-generated method stub
		return m_Result;
	}

	@Override
	public String str(int number) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rule reuduce(int number) {
		if (left.isReduce())
			return new Add(left.reuduce(number), right);
		if (right.isReduce())
			return new Add(left, right.reuduce(number));
		Result retLeft = left.getResult();
		Result retRight = right.getResult();
		if (retLeft.state && retRight.state)
			return new Rule(new Result(true, retLeft.res + retRight.res));
		return new Rule();
	}

}
