/**
 * This is a testing framework. Use it extensively to verify that your code is
 * working properly.
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

	private static void testAddOp() {

		AddOp ao = new AddOp();

		test(ao.toString().equals("+"), "Operator toString should be +.");

		test(ao.operate(3, 2) == 5.0, "Result of 3+2 should be 1.5.");
		test(ao.operate(-3, 2) == -1.0, "Result of (-3)+2 should be -1.0.");
		test(ao.operate(-3, -2) == -5.0, "Result of (-3)+(-2) should be -5.0.");
		test(ao.operate(3.55, 2.35) == 5.90, "Result of 3.55+2.35 should be 5.90.");
		double operate = ao.operate(3.55, -2.35);
		System.out.println(operate + "");
		test(operate == 1.20, "Result of 3.55+(-2.35) should be 1.2.");

	}

	private static void testDivideOp() {

		DivideOp dio = new DivideOp();

		test(dio.toString().equals("/"), "Operator toString should be /.");

		test(dio.operate(3, 2) == 1.5, "Result of 3/2 should be 1.5.");
		test(dio.operate(0, 2) == 0.0, "Result of 0/2 should be 0.0.");
		test(dio.operate(-3, 2) == -1.5, "Result of (-3)/2 should be -1.5.");
		test(dio.operate(-3, -2) == 1.5, "Result of (-3)/(-2) should be 1.5.");
		test(dio.operate(3, 0.0) == Double.POSITIVE_INFINITY, "Result of 3/0 should be INFINITY.");

	}

	private static void testExpTokenizer() {
		// TODO Auto-generated method stub

	}

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

		/*
		 * TODO Go for it! write your tests here for the PostfixCalculator
		 * class!
		 */
	}

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