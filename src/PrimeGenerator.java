import java.math.BigInteger; 
import java.util.Random;
import java.util.Scanner;

/**
 * 
 * @author Joseph Buchwald
 * Completion: 12/6/2016
 *
 */

public class PrimeGenerator {

	//Method: Primality test
	public static boolean primeTest(int n){
		
		int k = 1;
		double q = 0;

		boolean odd = false;

		// Loop through to find values for q and k
		while(odd == false){
			if(((n-1)/Math.pow(2, k)%2 != 0)){
				q = (n-1)/Math.pow(2, k);
				if((q/2)*2 == q){
					odd = true;
				}
			}else{
				k++;
			}
		}
		
		// Print values obtained
		System.out.println("n = "+n);
		System.out.println("k = "+k);
		System.out.println("q = "+q);
		
		//Big integer variables as well as counter
		String conclusion = "";
		BigInteger n1 = BigInteger.valueOf(n);
		BigInteger nSub1 = BigInteger.valueOf(n-1);
		BigInteger one = BigInteger.valueOf(1);
		BigInteger v2 = BigInteger.valueOf(0);
		double powJ = 0;
		int count = 0;
		
		// Loop through Rabin-Miller algorithm to determine conclusiveness
		while(count < 10){
		// Select random integer a, 1<a<n-1
		int a = 0;	//Initialize a
		Random rand = new Random();	//Declare random variable
		a = rand.nextInt(((n-2)-2) + 1) + 2;	// rand.nextInt((max - min) + 1) + min
		
		BigInteger a1 = BigInteger.valueOf(a);
		
		System.out.println("a = "+a);
		
		if(a1.pow((int)q).mod(n1).equals(one)){
			conclusion = ("Inconclusive! v1");
		}
		
		if(conclusion != "Inconclusive! v1"){
		for(int j = 0; j<k;j++){
			
			powJ = Math.pow(2, j);
			v2 = a1.pow(((int)powJ)*(int)q);
			
			if(v2.mod(n1).equals(nSub1)){
				conclusion = ("Inconclusive! v2");
				break;	// If determined to be inconclusive at that value (j), exit
			}else{
				conclusion = ("Composite!");
				}
			}
		}
			count++;
			System.out.println(conclusion);
		
			// If any iteration for a is composite, return false
			if(conclusion == "Composite!"){
				return false;
			}
		
		}
		// If composite is never a result through 10 iterations, assume prime, return true
		return true;
	}

}