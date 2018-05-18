package com.algorithm.study.ddd;

public class Default extends M{

	@Override
	public boolean isReduce() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String str(int number) {
		// TODO Auto-generated method stub
		return "D("+number+")";
	}

	@Override
	public Rule reuduce(int number) {
		// TODO Auto-generated method stub
		return new Rule(new Result(true,number+""));
	}

}
