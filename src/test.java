
import crypto.Prime;
import java.math.BigInteger;



/**
 * An internal class for testing VIPER methods.
 * @author Jack
 * @version 1.4 Alpha 
 */
public class test {
        
    /**
     * @param args The command line arguments
     */
    public static void main(String[] args) {
        
        int[] testVectors = Prime.eratosthenes(2000);
        for (int i: testVectors) {
            if (i > 25) {
                System.out.println(Prime.strongLucas(new BigInteger(""+i), false));
            }
        }
        
    }
    
}