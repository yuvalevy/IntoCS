import java.util.Scanner;

public class Ex4 {
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		int nthFib = reader.nextInt();

		// setting first two numbers
		int first = 1;
		int second = 1;

		int temp = 0;

		System.out.println(first);
		
		for (int i = 1; i < nthFib; i = i+1) {
			temp = first + second; // calculating next number
			
			System.out.println(second);
			
			// updating previous two numbers			
			first = second;
			second = temp;
		}
	}
}