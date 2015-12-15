import java.util.Scanner;

public class Ex7 {

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		int input = reader.nextInt();

		if (0 < input) {
			// printing upper part, including middle line
			for (int i = 1; i <= ((2*input - 1) / 2) + 1; i = i+1) {
				for (int k = (2*input - 1) / 2; k > ((2*input - 1) / 2) - (i - 1); k = k-1) {
					System.out.print(' ');
			  }
				for (int j = 1; j <= ((2*input - 1) - ((i - 1) *2)); j = j+1) {
					System.out.print('*');
				}
				System.out.println();
			}

			// printing lower part, after middle line
			for (int i = (2*input - 1) / 2 ; i >= 1 ; i = i-1) { // just need to reverse the printing, which is dependet on i
				for (int k = (2*input - 1) / 2; k > ((2*input - 1) / 2) - (i - 1); k = k-1) {
					System.out.print(' ');
				}
				for (int j = 1; j <= ((2*input - 1) - ((i - 1) *2)); j = j+1) {
					System.out.print('*');
				}
				System.out.println();
			}
		} else {
			System.out.println("ilegal number");
		}
	}
}