/**
 * 
 * This class is a testing class for this project. 
 * 
 * @author Ram and Yuval
 */
public class Tester {

	private static boolean testPassed = true;
	private static int testNum = 0;

	/**
	 * This entry function will test all classes created in this assignment.
	 *
	 * @param args
	 *            command line arguments
	 */
	public static void main(String[] args) {

		// Each function here should test a different class.
		testValueToken();
		testAddOp();
		testSubtractOp();
		testPowOp();
		testDivideOp();
		testMultiOp();

		testExpTokenizer();
		testPostfixCalculator();

		// Notifying the user that the code have passed all tests.
		if (testPassed) {
			System.out.println("All " + testNum + " tests passed!");
		}
	}

	/**
	 * Checks the SubtractOp class.
	 */
	private static void testSubtractOp() {
		SubtractOp so = new SubtractOp();

		test(so.toString().equals("-"), "Operator toString should be -.");

		test(so.operate(3, 2) == 1.0, "Result of 3-2 should be 1.5.");
		test(so.operate(-3, 2) == -5.0, "Result of (-3)-2 should be -5.0.");
		test(so.operate(-3, -2) == -1.0, "Result of (-3)-(-2) should be -1.0.");
		test(so.operate(3.55, 2.35) == 3.55 - 2.35, "Result of 3.55-2.35 should be 1.2.");
		test(so.operate(3.55, -2.35) == 5.9, "Result of 3.55-(-2.35) should be 5.9.");

		
	}

	/**
	 * This utility function will count the number of times it was invoked. In
	 * addition, if a test fails the function will print the error message.
	 *
	 * @param exp
	 *            The actual test condition
	 * @param msg
	 *            An error message, will be printed to the screen in case the
	 *            test fails.
	 */
	private static void test(boolean exp, String msg) {
		testNum++;

		if (!exp) {
			testPassed = false;
			System.out.println("Test " + testNum + " failed: " + msg);
		}
	}

	/**
	 * Checks the AddOp class.
	 */
	private static void testAddOp() {

		AddOp ao = new AddOp();

		test(ao.toString().equals("+"), "Operator toString should be +.");

		test(ao.operate(3, 2) == 5.0, "Result of 3+2 should be 1.5.");
		test(ao.operate(-3, 2) == -1.0, "Result of (-3)+2 should be -1.0.");
		test(ao.operate(-3, -2) == -5.0, "Result of (-3)+(-2) should be -5.0.");
		test(ao.operate(3.55, 2.35) == 5.90, "Result of 3.55+2.35 should be 5.90.");
		test(ao.operate(3.55, -2.35) == 3.55 - 2.35, "Result of 3.55+(-2.35) should be 1.2.");

	}

	/**
	 * Checks the DivideOp class.
	 */
	private static void testDivideOp() {

		DivideOp dio = new DivideOp();

		test(dio.toString().equals("/"), "Operator toString should be /.");

		test(dio.operate(3, 2) == 1.5, "Result of 3/2 should be 1.5.");
		test(dio.operate(0, 2) == 0.0, "Result of 0/2 should be 0.0.");
		test(dio.operate(-3, 2) == -1.5, "Result of (-3)/2 should be -1.5.");
		test(dio.operate(-3, -2) == 1.5, "Result of (-3)/(-2) should be 1.5.");
		test(dio.operate(3, 0.0) == Double.POSITIVE_INFINITY, "Result of 3/0 should be INFINITY.");

	}

	/**
	 * Checks the ExpTokenizer class.
	 */
	private static void testExpTokenizer() {
		ExpTokenizer et = new ExpTokenizer("1 + - * / ^ 3.5");
		
		test(et.nextElement() instanceof ValueToken, "should be ValueToken");
		test(et.nextElement() instanceof AddOp, "should be AddOp");
		test(et.nextElement() instanceof SubtractOp, "should be SubtractOp");
		test(et.nextElement() instanceof MultiplyOp, "should be MultiplyOp");
		test(et.nextElement() instanceof DivideOp, "should be DivideOp");
		test(et.nextElement() instanceof PowOp, "should be PowOp");
		test(et.nextElement() instanceof ValueToken, "should be ValueToken");
		
		et = new ExpTokenizer("$");
		try {
			et.nextElement();
		} catch (Exception e) {
			test(e instanceof ParseException, "error should be of type ParseException");
			test(e.getMessage().equals("SYNTAX ERROR: invalid token $"), "error should be 'SYNTAX ERROR: invalid token $'");
		}
		
		et = new ExpTokenizer("1.1.1");
		try {
			et.nextElement();
		} catch (Exception e) {
			test(e instanceof ParseException, "error should be of type ParseException");
			test(e.getMessage().equals("SYNTAX ERROR: invalid number 1.1.1"), "error should be 'SYNTAX ERROR: invalid number 1.1.1'");
		}

		
	}

	/**
	 * Checks the MultiplyOp class.
	 */
	private static void testMultiOp() {

		MultiplyOp po = new MultiplyOp();

		test(po.toString().equals("*"), "Operator toString should be *.");

		test(po.operate(2, 3) == 6.0, "Result of 2*3 should be 6.0.");
		test(po.operate(2, 0) == 0.0, "Result of 2*0 should be 0.0.");
		test(po.operate(-2, 3) == -6.0, "Result of (-2)*3 should be -6.0.");

	}

	/**
	 * Checks the PostfixCalculator class.
	 */
	private static void testPostfixCalculator() {

		PostfixCalculator pc = new PostfixCalculator();
		pc.evaluate("2 3 +");
		test(pc.getCurrentResult() == 5.0, "Result '2 3 +' should eual 5.0");
		pc.evaluate("3 5 -");
		test(pc.getCurrentResult() == -2.0, "Result '3 5 -' should eual -2.0");
		pc.evaluate("6 2 *");
		test(pc.getCurrentResult() == 12.0, "Result '6 2 *' should eual 12.0");
		pc.evaluate("10 4 /");
		test(pc.getCurrentResult() == 2.5, "Result '10 4 /' should eual 2.5");
		pc.evaluate("2 4 ^");
		test(pc.getCurrentResult() == 16.0, "Result '2 4 ^' should eual 16.0");
		pc.evaluate("2 3 + 4 2 - *");
		test(pc.getCurrentResult() == 10.0, "Result '2 3 + 4 2 - *' should eual 10.0");
		pc.evaluate("2 3 ^ 4 2 * / 7 -");
		test(pc.getCurrentResult() == -6.0, "Result '2 3 ^ 4 2 * / 7 -' should eual -6.0");
		
		try {
			pc.evaluate("1 2 3 4 5");
		} catch (Exception e) {
			test(e instanceof ParseException, "error should be of type ParseException");
			test(e.getMessage().equals("SYNTAX ERROR: invalid expression"), "error should be 'SYNTAX ERROR: invalid expression'");
		}
		
		try {
			pc.evaluate("1 2 * ^");
		} catch (Exception e) {
			test(e instanceof ParseException, "error should be of type ParseException");
			test(e.getMessage().equals("SYNTAX ERROR: cannot perform operation ^"), "error should be 'SYNTAX ERROR: cannot perform operation ^'");
		}
		
		try {
			pc.evaluate(" ");
		} catch (Exception e) {
			test(e instanceof ParseException, "error should be of type ParseException");
			test(e.getMessage().equals("SYNTAX ERROR: invalid expression"), "error should be 'SYNTAX ERROR: invalid expression'");
		}
	}

	/**
	 * Checks the PowOp class.
	 */
	private static void testPowOp() {

		PowOp po = new PowOp();

		test(po.toString().equals("^"), "Operator toString should be ^.");

		test(po.operate(2, 3) == 8.0, "Result of 2^3 should be 8.0.");
		test(po.operate(2, 0) == 1.0, "Result of 2^0 should be 1.0.");
		test(po.operate(0, 3) == 0.0, "Result of 0^3  should be 0.0.");
		test(po.operate(-2, 3) == -8.0, "Result of (-2)^3 should be -8.0.");
		test(po.operate(-2, 2) == 4.0, "Result of (-2)^2 should be 4.0.");
		test(po.operate(2, -2) == 0.25, "Result of 2^(-2) should be 0.25.");
		test(po.operate(4, 0.5) == 2.0, "Result of 4^(0.5) should be 2.");
		test(po.operate(4, -0.5) == 0.5, "Result of 4^(-0.5) should be 0.5.");

	}

	/**
	 * Checks the ValueToken class.
	 */
	private static void testValueToken() {

		ValueToken tv = new ValueToken(5.1);

		test(tv.getValue() == 5.1, "Value should be 5.1.");
		test(tv.toString().equals("5.1"), "Value toString should be 5.1.");

		tv = new ValueToken(5);
		test(tv.getValue() == 5.0, "Value should be 5.0.");
		test(tv.toString().equals("5.0"), "Value toString should be 5.0.");

		tv = new ValueToken(-2.5);
		test(tv.getValue() == -2.5, "Value should be -2.5.");
		test(tv.toString().equals("-2.5"), "Value toString should be -2.5.");

		tv = new ValueToken(Double.MAX_VALUE);
		test(tv.getValue() == Double.MAX_VALUE, "Value should be " + Double.MAX_VALUE + ".");
		test(tv.toString().equals(Double.MAX_VALUE + ""), "Value toString should be " + Double.MAX_VALUE + ".");

		tv = new ValueToken(Double.MIN_VALUE);
		test(tv.getValue() == Double.MIN_VALUE, "Value should be " + Double.MIN_VALUE + ".");
		test(tv.toString().equals(Double.MIN_VALUE + ""), "Value toString should be " + Double.MIN_VALUE + ".");

	}
}