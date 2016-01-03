
public class ValueToken extends CalcToken {

	private double token;

	public ValueToken(double value) {
		this.token = value;
	}

	public double getValue() {
		return this.token;
	}

	@Override
	public String toString() {
		return this.token + "";
	}

}
