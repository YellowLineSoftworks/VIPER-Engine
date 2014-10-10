
import java.math.BigInteger;
import crypto.Prime;

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
        /*int[] era = crypto.Prime.eratosthenes(200);
        for(int x = 0; x < era.length; x++) {
            System.out.println(crypto.Prime.solovayStrassen(new BigInteger(era[x] + ""),10));
        }*/
        System.out.println(Prime.jacobi(new BigInteger(""+888), new BigInteger(""+1999)));
    }
    
}