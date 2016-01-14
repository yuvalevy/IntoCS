import java.util.Enumeration;

/**
 * If direction is true, we will tokenize from left to right, if direction is
 * false, then we will tokenize from right to left.
 *
 */
public class ExpTokenizer extends Object implements Enumeration<Object> {
	// fields
	private String[] result;
	private boolean direction;
	private int index;

	/**
	 * Default constructor. Sets direction to be true
	 * 
	 * @param exp
	 *            Given expression
	 */
	public ExpTokenizer(String exp) {
		this.result = exp.split(" ");
		this.direction = true;
		this.index = 0;
	}

	/**
	 * Builds a new ExpTokenizer. Splits the given expression by ' '
	 * 
	 * @param exp
	 *            Given expression
	 * @param direction
	 *            true for left to right, false otherwise
	 */
	public ExpTokenizer(String exp, boolean direction) {
		this.result = exp.split(" ");
		this.direction = direction;
		if (!this.direction) {
			this.index = this.result.length - 1;
		} else {
			this.index = 0;
		}
	}

	/**
	 *
	 * @return the count of current tokens
	 */
	public int countTokens() {
		if (this.direction) {
			return (this.result.length - this.index);
		} else {
			return (this.index + 1);
		}
	}

	/**
	 *
	 * @return true if tokenizer has more elements, false otherwise
	 * 
	 */
	@Override
	public boolean hasMoreElements() {
		if (this.direction) {
			return (this.index != this.result.length);
		} else {
			return (this.index >= 0);
		}
	}

	/**
	 *
	 * @return true if tokenizer has more tokens, false otherwise
	 */
	public boolean hasMoreTokens() {
		return hasMoreElements();
	}

	/**
	 *
	 * @return the next element of the tokenize
	 *
	 * @throws ParseException
	 *             - if expression/number/token is invalid
	 */
	@Override
	public Object nextElement() {

		CalcToken resultToken = null;

		String token = nextToken();

		if (token.equals("+")) {
			resultToken = new AddOp();
		} else if (token.equals("*")) {
			resultToken = new MultiplyOp();
		} else if (token.equals("-")) {
			resultToken = new SubtractOp();
		} else if (token.equals("/")) {
			resultToken = new DivideOp();
		} else if (token.equals("^")) {
			resultToken = new PowOp();
		} else {
			try {
				resultToken = new ValueToken(Double.parseDouble(token));

			} catch (Exception e) {

				if (token.length() > 0) {
					if (Character.isDigit(token.charAt(0))) {
						throw new ParseException("invalid number " + token);
					} else {
						throw new ParseException("invalid token " + token);
					}
				} else {
					throw new ParseException("invalid expression");
				}

			}
		}

		return resultToken;
	}

	/**
	 *
	 * @return next token as a string
	 */
	public String nextToken() {
		String ret;
		if (this.direction) {
			ret = this.result[this.index];
			this.index++;
		}

		else {
			ret = this.result[this.index];
			this.index--;
		}
		return ret;
	}
}