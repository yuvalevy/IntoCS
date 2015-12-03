import java.util.Scanner;

public class Ex5 {
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		int num = reader.nextInt();

		int counter = 1;
		for (int i = 0; i <= num; i = i + 11) { // we need only number that divided by 11, so every iteration will add 11 to the previous number

			int dig = i;
			int sum = 0; // the sum of all i's digits
			
			// adding each digit to sum 
			while (dig != 0) {
				sum = sum + (dig % 10);
				dig = dig / 10;
			}
			if (sum != 0) {
				counter = counter * sum;
			}
		}
		if (num < 11) {
			System.out.println(0);
		} else {
			System.out.println(counter);
		}
	}
}