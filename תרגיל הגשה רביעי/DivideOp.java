
public class DivideOp extends BinaryOp {

	@Override
	public double operate(double left, double right) {
		// TODO: divide 0
		return left / right;
	}

	@Override
	public String toString() {
		return "/";
	}

}
