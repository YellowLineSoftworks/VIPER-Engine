
import crypto.Prime;
import graphics3D.Vector3D;
import java.math.BigInteger;



/**
 * An internal class for testing VIPER methods.
 * @author Jack
 * @version 1.5 Alpha 
 */
public class test {
        
    /**
     * @param args The command line arguments
     */
    public static void main(String[] args) {
        
        /*
        int[] testVectors = Prime.eratosthenes(2000);
        for (int i: testVectors) {
            if (i > 25) {
                System.out.println(Prime.strongLucas(new BigInteger(""+i), false));
            }
        }
        */
        Vector3D testVector = new Vector3D(3, 4, 5);
        testVector.scale(2,3,1);
        System.out.println(testVector.getDirection()[0] + " " + testVector.getDirection()[1] + " " + testVector.getDirection()[2]);
        
    }
    
}