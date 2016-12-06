import java.math.BigInteger;

public class PrivateKey {

	//empty constructor
	public PrivateKey(){}
	
	public BigInteger pKey(BigInteger N, BigInteger e){
		
		BigInteger R_2= N;
		BigInteger R_1= e;
		BigInteger R;
		BigInteger X;
		BigInteger Y_1 = BigInteger.valueOf(1);
		BigInteger Y_2= BigInteger.valueOf(0);
		BigInteger Y;
		BigInteger Q;
		boolean bool = true;
		
		do{
			//find Q using the two previous R's
			Q = R_2.divide(R_1);
			
			//find the new R (modulus of previous two)
			R = R_2.mod(R_1);
			
			//get new Y
			//do two separate operations for BigInteger
			X = Q.multiply(Y_1);
			Y = Y_2.subtract(X);
			
			//move the numbers down
			R_2 = R_1;
			R_1 = R;
			
			Y_2 = Y_1;
			Y_1 = Y;
			
			if(R_1 == BigInteger.valueOf(0)){
				//System.out.println("false");
				bool = false;
			}
		
		//current R is now set to previous	
		}while(bool == true);
		
		return Y_2;
		
	}
	
}