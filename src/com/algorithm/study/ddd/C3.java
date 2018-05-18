package com.algorithm.study.ddd;

public class C3 extends M{

	@Override
	public boolean isReduce() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Result getResult() {
		// TODO Auto-generated method stub
		return m_Result;
	}

	@Override
	public String str(int number) {
		// TODO Auto-generated method stub
		return "C3("+number+")";
	}

	@Override
	public Rule reuduce(int number) {
		if(String.valueOf(number).contains("3"))
			return new Rule(new Result(true,"Fizz"));
		return new Rule(new Result(false,""));
	}

}
