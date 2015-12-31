/**
 * This is a testing framework. Use it extensively to verify that your code is working
 * properly.
 */
public class Tester {

	private static boolean testPassed = true;
	private static int testNum = 0;
	
	/**
	 * This entry function will test all classes created in this assignment.
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		
		// Each function here should test a different class.
		testValueToken();
		testPostfixCalculator();
		/* TODO - write a function for each class */
		
		
		// Notifying the user that the code have passed all tests. 
		if (testPassed) {
			System.out.println("All " + testNum + " tests passed!");
		}
	}

	/**
	 * This utility function will count the number of times it was invoked. 
	 * In addition, if a test fails the function will print the error message.  
	 * @param exp The actual test condition
	 * @param msg An error message, will be printed to the screen in case the test fails.
	 */
	private static void test(boolean exp, String msg) {
		testNum++;
		
		if (!exp) {
			testPassed = false;
			System.out.println("Test " + testNum + " failed: "  + msg);
		}
	}
	
	/**
	 * Checks the ValueToken class.
	 */
	private static void testValueToken() {
		
		ValueToken t1 = new ValueToken(5.1);
		
		test(t1.getValue() == 5.1, "Value should be 5.1.");
		test(t1.toString().equals("5.1"), "Value toString should be 5.1.");
				
		/* TODO add more tests to the ValueToken class! */
	}

	/**
	 * Checks the PostfixCalculator class.
	 */
	private static void testPostfixCalculator() {
		
		/* TODO Go for it! write your tests here for the PostfixCalculator class! */
	}
}