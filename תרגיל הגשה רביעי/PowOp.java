
public class PowOp extends BinaryOp {

	@Override
	public double operate(double left, double right) {
		return Math.pow(left, right);
	}

	@Override
	public String toString() {
		return "^";
	}

}
