import java.util.Scanner;

public class Ex3 {
	public static void main(String[] args) {
		
		Scanner reader = new Scanner(System.in);

		/* we assume evenGreater > evenSmaller
		 * we assume oddGreater > oddSmaller			
		 */
		int evenGreater = 0, evenSmaller = 0, oddGreater = 0, oddSmaller = 0;

		// Getting input from user
		int input = reader.nextInt();
		while(input != 0) {
			if(input%2 == 0) { // 'input' is even
				if(input > evenSmaller) {
					if(input > evenGreater) {
						evenSmaller = evenGreater;
						evenGreater = input;											
					}
					else {
						evenSmaller = input;
					}
				}				
			} 
			else { // 'input' is odd
				if(input > oddSmaller) {
					if(input > oddGreater) {
						oddSmaller = oddGreater;
						oddGreater = input;											
					}
					else {
						oddSmaller = input;
					}
				}
			}		
			input = reader.nextInt();
		}
		// calculating and prining Geometric mean	
		double gm = Math.pow((evenGreater*evenSmaller*oddGreater*oddSmaller),0.25);	
		System.out.println(String.format("%.3f", gm));
	}
}