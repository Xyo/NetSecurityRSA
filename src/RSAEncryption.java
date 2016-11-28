import java.math.BigInteger;
import java.util.ArrayList;

/**
 * Created by Jeremy on 11/26/2016.
 */
public class RSAEncryption {

    private BigInteger a;
    private BigInteger b;
    private BigInteger n;


    // Returns the list of powers of 2 that add up to the exponent
    // i.e. x^11 = (x^1)(x^2)(x^8)
    // 11 = 1011
    private static ArrayList<Integer> getExponentPowers(int exponent){
        if(exponent < 1 ) return null;

        ArrayList<Integer> exponentList = new ArrayList<>();
        String binary = Integer.toBinaryString(exponent);

        for(int i = exponent; i > 0;){
            i = (int)(i/2);
        }

    }
}
