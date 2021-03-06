/**
 * This class represents the / operator for two doubles
 * 
 * @author Ram and Yuval
 * 
 *
 */
public class DivideOp extends BinaryOp {

	/**
	 * Divides the left by right variables
	 * 
	 * @param left
	 *            the left operand.
	 * @param right
	 *            the right operand.
	 * @return the division of left by right.
	 */
	@Override
	public double operate(double left, double right) {
		return left / right;
	}

	@Override
	public String toString() {
		return "/";
	}

}
