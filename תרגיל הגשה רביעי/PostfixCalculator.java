/**
 * The class represent a postfix type calculator
 * 
 * @author Ram and Yuval
 *
 */
public class PostfixCalculator extends Calculator {

	/**
	 * Calculates the expression and saves the result in superclass
	 * 
	 * @see Calculator#evaluate(java.lang.String)
	 * @see Calculator#getCurrentResult()
	 * 
	 * @throws ParseException - if expression is invalid
	 */
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
				double right = (Double) stack.pop();
				double left = (Double) stack.pop();

				stack.push(((BinaryOp) token).operate(left, right));

			} else { // means token is a number

				stack.push(Double.parseDouble(token.toString()));

			}

		}

		if (stack.size != 1) {
			throw new ParseException("invalid expression");
		}
		this.result = (Double) stack.pop();
	}
}
