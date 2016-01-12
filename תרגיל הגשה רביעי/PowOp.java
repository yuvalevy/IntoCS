/**
 * This class represents the ^ operator for two doubles
 * 
 * @author Ram and Yuval
 * 
 *
 */
public class PowOp extends BinaryOp {

	@Override
	/**
	 * Powers the left by right variables
	 * 
	 * @param left
	 *            the left operand.
	 * @param right
	 *            the right operand.
	 * @return the power of left by right.
	 */
	public double operate(double left, double right) {
		return Math.pow(left, right);
	}

	@Override
	public String toString() {
		return "^";
	}

}
