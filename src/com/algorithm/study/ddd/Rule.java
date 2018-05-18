package com.algorithm.study.ddd;

public class Rule {
	public Result m_Result;

	public Rule reuduce(int number) {
		return new Rule();
	};

	public boolean isReduce() {
		return false;
	};

	public Result getResult() {
		return m_Result;
	};

	public String str(int number) {
		String state = getResult().state ? "true" : "false";
		return "(" + state + "," + getResult().res + ")";
	};

	public Rule() {
	}

	public Rule(Result result) {
	}
}
