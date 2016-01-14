/**
 * This class represents the + operator for two doubles
 *
 * @author Ram and Yuval
 *
 */
public class AddOp extends BinaryOp {

	/**
	 * Sums the left, right variables
	 *
	 * @param left
	 *            the left operand.
	 * @param right
	 *            the right operand.
	 * @return the sum of left and right.
	 */
	@Override
	public double operate(double left, double right) {
		return left + right;
	}

	@Override
	public String toString() {
		return "+";
	}

}
