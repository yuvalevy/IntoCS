
public class DivideOp extends BinaryOp {

	@Override
	public double operate(double left, double right) {
		return left / right;
	}

	@Override
	public String toString() {
		return "/";
	}

}
