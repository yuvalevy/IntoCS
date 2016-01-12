/**
 * This class represents the * operator for two doubles
 * 
 * @author Ram and Yuval
 * 
 *
 */
public class MultiplyOp extends BinaryOp {

	@Override
	/**
	 * Multiply the left and right variables
	 * 
	 * @param left
	 *            the left operand.
	 * @param right
	 *            the right operand.
	 * @return the product of left by right.
	 */
	public double operate(double left, double right) {
		return left * right;
	}

	@Override
	public String toString() {
		return "*";
	}

}
