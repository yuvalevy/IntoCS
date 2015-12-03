import java.util.Scanner;

public class Ex6 {
	public static void main(String[] args) {

		Scanner reader = new Scanner(System.in);
		int n = reader.nextInt();

		int sqrCount = 0;

		for (int size = 1; size <= n; size = size + 2) { // A loop for each
															// size. (+2) for
															// odds
			for (int i = 1; i <= ((n - size) + 1); i=i+1) { // loop for rows;
															// going until the
															// last row that can
															// fit a square at
															// of size
															// 'size'x'size'
				for (int j = 1; j <= ((n - size) + 1); j=j+1) { // loop for
																// columns;
																// going until
																// the last
																// column that
																// can fit a
																// square at of
																// size
																// 'size'x'size'
					sqrCount++;
				}
			}
		}
		System.out.println(sqrCount);
	}
}