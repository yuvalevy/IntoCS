/**
 * 
 * The class represent a basic calculator
 * 
 * @author Ram and Yuval
 *
 */
public abstract class Calculator {	
	
	protected double result;
	
	/**
	 * Calculates the given expression
	 * @param expr
	 */
	public abstract void evaluate(String expr);

	/**
	 * 
	 * @return Calculator's current result
	 */
	public double getCurrentResult() {
		return result;
	}

}
