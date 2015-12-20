public class NumberPartition {

	public static boolean isNumberPartition(int n, String s) {
		if ((s == null) | (s.length() != n)) {
			return false;
		}

		int countOnes = 0, countZeros = 0, sumOnes = 0, sumZeros = 0, powerOnes = 0, powerZeros = 0;

		for (int i = 0; i < n; i = i + 1) {

			if (s.charAt(i) == '1') {
				countOnes = countOnes + 1;
				sumOnes = sumOnes + i + 1;
				powerOnes = powerOnes + ((i + 1) * (i + 1));
			} else if (s.charAt(i) == '0') {
				countZeros = countZeros + 1;
				sumZeros = sumZeros + i + 1;
				powerZeros = powerZeros + ((i + 1) * (i + 1));
			} else {
				return false;
			}
		}
		return (countOnes == countZeros) && (sumOnes == sumZeros) && (powerOnes == powerZeros);
	}

	/*
	 * Check if a characteristic string defines a partition of the numbers 1...n
	 * such that: (1) both sides are of equal length (2) both sides are of equal
	 * sum (3) both sides have equal sums of squared elements. returns false if
	 * arguments are null / "incorrect"
	 */

	/**
	 * Function prints all numberPartions strings, using recursive function to
	 * check for all options
	 *
	 * @param n
	 *            the last element in the numbers' series
	 */
	public static void numberPartition(int n) {
		if (n > 0) {
			numberPartition(n, "");
		}
	}

	/**
	 * Function goes through all the options to build N length 0,1 strings and
	 * prints them if they're partitions
	 *
	 * @param n
	 *            the last element in the numbers' series
	 * @param s
	 *            01 String
	 */
	private static void numberPartition(int n, String s) {
		if ((n % 2) == 0) { // Test can't pass for an odd length
			if (n == s.length()) {
				if (isNumberPartition(n, s)) {
					System.out.println(s);
				}
			} else {
				numberPartition(n, '1' + s);
				numberPartition(n, '0' + s);
			}
		}
	}
}
