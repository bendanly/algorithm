package com.algorithm.study.ddd;

public class Or extends Rule {
	private Rule left;
	private Rule right;

	public Or() {

	}

	public Or(Rule left, Rule right) {
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
		return "Or (" + left.str(number) + "," + right.str(number) + ")";
	}

	@Override
	public Rule reuduce(int number) {
		if (left.isReduce())
			return new Or(left.reuduce(number), right);
		if (right.isReduce())
			return new Or(left, right.reuduce(number));
		Result retLeft = left.getResult();
		Result retRight = right.getResult();
		// TODO
		if (retLeft.state || retRight.state)
			return new Rule(new Result(true, retLeft.res + retRight.res));
		return new Rule();
	}

}
