import java.util.Scanner;

public class PostfixCalculator extends Calculator {

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

				int right = (int) stack.pop();
				int left = (int) stack.pop();

				stack.push(((BinaryOp) token).operate(left, right));

			} else { // means token is a number

				stack.push(token.toString());

			}

		}
	}

	@Override
	public double getCurrentResult() {

		return 0;
	}
}
