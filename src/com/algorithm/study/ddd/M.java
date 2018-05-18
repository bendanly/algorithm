package com.algorithm.study.ddd;

public class M extends Rule {
	public M() {

	}

	public M(int mod, String res) {

	}

	private int Mod;
	private String Res;

	@Override
	public boolean isReduce() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Result getResult() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String str(int number) {
		// TODO Auto-generated method stub
		return "M" + Mod + "(" + number + ")";
	}

	@Override
	public Rule reuduce(int number) {
		if (0 == number % Mod) {
			return new Rule(new Result(true, Res));
		}
		return new Rule(new Result(false, ""));
	}

}
