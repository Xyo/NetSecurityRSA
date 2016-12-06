import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Jeremy on 11/26/2016.
 */
public class Encryptor {

    private HashMap<BigInteger, List<Integer>> exponentList = new HashMap<>();


    public static void main(String[] args){
        BigInteger message = new BigInteger("1976620216402300889624482718775150");
        String bigValueN = "22953686867719691230002707821868552601124472329079";
        String bigValueD = "30762542250301270692051460539586166927291732754961";
        String bigValueE = "65537";

        String smallValueN = "86609";
        String smallValueE = "17";
        String smallValueD = "65777";
        // should be 44
        BigInteger cipherText = encrypt(message, new BigInteger(bigValueE), new BigInteger(bigValueN));
        System.out.println(cipherText);


        BigInteger plainText = decrypt(cipherText, new BigInteger(bigValueD), new BigInteger(bigValueN));
        System.out.println(plainText);
    }

    // Returns the decrypted result
    public static BigInteger decrypt(BigInteger cipherText, BigInteger d, BigInteger modN){
        return encrypt(cipherText, d, modN);
    }

    // C = M^e mod n
    // breaks the number up to smaller base^exponents, multiplies their mod n results together
    // (message^exponent) mod n = [(base^exponentA) mod n] * [(base^exponentB] mod n
    public static BigInteger encrypt(BigInteger message, BigInteger exponent, BigInteger n){
        //return toPowerOf(message, exponent).mod(n);

        return bigModulus(message, exponent, n);
    }

    private static BigInteger toPowerOf(BigInteger base, BigInteger exponent) {
        BigInteger result = BigInteger.ONE;
        while (exponent.signum() > 0) {
            if (exponent.testBit(0)) result = result.multiply(base);
            base = base.multiply(base);
            exponent = exponent.shiftRight(1);
        }
        return result;
    }

    //For each smaller number, mod n and then multiply with the running result
    private static BigInteger bigModulus(BigInteger message, BigInteger exponent, BigInteger n){
        BigInteger cipherText = BigInteger.ONE;
        List<BigInteger> smallerValues = breakNumberUp(message, exponent);
        for(BigInteger num : smallerValues){
            BigInteger modResult = num.mod(n);
            cipherText = cipherText.multiply(modResult);
        }
        return cipherText.mod(n);

    }

    // Returns the breakup of the big number into smaller numbers
    // x^11 = (x^1)(x^2)(x^8)
    private static List<BigInteger> breakNumberUp(BigInteger base, BigInteger exponent){
        List<Integer> exponentList = getExponentPowers(exponent);
        List<BigInteger> numberBreakup = new ArrayList<>();
        // For each power of two of the num, create a new bigint of the base to the exponent
        for(Integer smallerExponent : exponentList){
            numberBreakup.add(base.pow(smallerExponent));
        }
        return numberBreakup;
    }

    // Returns a list of powers of 2 that add up to the exponent (the two's complement)
    private static List<Integer> getExponentPowers(BigInteger exponent){
        if(exponent.compareTo(BigInteger.valueOf(1)) < 0 ) return null;

        List<Integer> exponentList = new ArrayList<>();

        String binary = exponent.toString(2);
        String binaryReversed = new StringBuilder(binary).reverse().toString();

        // For each 1 in binary string, record the value
        int valueOfBinary = 1;
        for(int i=0; i<binaryReversed.length(); i++){
            if(binaryReversed.charAt(i) == '1'){
                exponentList.add(valueOfBinary);
            }
            // For each place, double the binary value
            valueOfBinary *= 2;
        }
        return exponentList;
    }
}
