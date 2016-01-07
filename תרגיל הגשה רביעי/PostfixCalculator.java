import java.util.Scanner;

public class PostfixCalculator extends Calculator {

	private double result;

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);

		String exp = in.nextLine();

		PostfixCalculator pf = new PostfixCalculator();
		pf.evaluate(exp);
		System.out.println(pf.getCurrentResult());
		in.close();
	}

	@Override
	public void evaluate(String expr) {

		ExpTokenizer exp = new ExpTokenizer(expr, true);

		StackAsArray stack = new StackAsArray();

		while (exp.hasMoreTokens()) {

			CalcToken token = (CalcToken) exp.nextElement(); // functions always
																// returns
																// ClacToken
																// object that
																// is not null
			if (token instanceof BinaryOp) { // means token is an operator
				if (stack.size < 2) {
					throw new ParseException("cannot perform operation " + token.toString());

				}
				double right = (double) stack.pop();
				double left = (double) stack.pop();

				stack.push(((BinaryOp) token).operate(left, right));

			} else { // means token is a number

				stack.push(Double.parseDouble(token.toString()));

			}

		}

		if (stack.size != 1) {
			throw new ParseException("invalid expression");
		}
		this.result = (double) stack.pop();
	}

	@Override
	public double getCurrentResult() {

		return this.result;
	}
}
