/**
 * The class represent a numeric value token
 * 
 * @author Ram and Yuval
 *
 */
public class ValueToken extends CalcToken {

	private double token;

	/**
	 * Creates new value token
	 * @param value token numeric value
	 */
	public ValueToken(double value) {
		this.token = value;
	}

	/**
	 * @return token numeric
	 */
	public double getValue() {
		return this.token;
	}

	@Override
	public String toString() {
		return this.token + "";
	}

}
