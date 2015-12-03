import java.util.Scanner;

public class Ex2 {
	public static void main(String[] args) {
		
   Scanner reader = new Scanner(System.in);
   int num = reader.nextInt();

    for(int i = 2; i*i <= num; i = i+1) {
      if(num%i == 0) { // checking if 'i' divides 'num'
        boolean isPrime = true;
				//checking if 'i' is prime
        for (int j = 2 ; j*j <= i ; j = j+1) {
					if(i%j == 0) { // checking 'j' divides 'i', means 'i' is not prime
            isPrime = false;
          }
        }
        /*
         * If 'i' is prime we will print 'i' and divide 'num' by 'i' and start the loop again to find the other prime factors.
         */
        if(isPrime) {
          num = num / i;
          System.out.println(i);
          i = 1; //we assign 1 to 'i' because at the next iteration the loop will increase it to 2
        }
      }
    }
    System.out.println(num); // num is the last prime factor left after dividing by all previous prime factors
  }
}