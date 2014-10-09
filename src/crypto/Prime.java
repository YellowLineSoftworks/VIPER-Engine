package crypto;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Random;

/**
 * A class containing functions for generating primes and testing primality.
 * @author Jack
 * @version 1.4 Alpha
 */
public class Prime {
        
    /**
     * Generates all primes less than the number 'n' using the Sieve of Eratosthenes
     * @param n The maximum value for your primes
     * @return An integer array containing all primes less than the value
     */
    public static int[] eratosthenes(int n) {
        
        //Fill an array with all numbers under n
        int[] possiblePrimes = new int[n-1];
        for(int x = 0; x < n-1; x++){
            possiblePrimes[x] = x + 2;
        }
        //Iterate through and remove all multiples of the numbers it encounters
        for(int x = 0; x < possiblePrimes.length; x++) {
            int mul = possiblePrimes[x]*2;
            while (mul <= possiblePrimes[possiblePrimes.length - 1]) {
                //Remove the multiple from the array
                int index = Arrays.binarySearch(possiblePrimes, mul);
                if (index >= 0) {
                    int[] temp = new int[possiblePrimes.length - 1];
                    System.arraycopy(possiblePrimes, 0, temp, 0, index);
                    System.arraycopy(possiblePrimes, index+1, temp, index, temp.length - index);
                    possiblePrimes = temp;
                }
                //Jump to next multiple
                mul += possiblePrimes[x];
            }
        }
        return possiblePrimes;
                
    }
    
    /**
     * Performs a Fermat primality test on the given BigInteger.
     * @param n The BigInteger to test.
     * @return True if it is a Fermat probable prime using the internally generated number, or false if not.
     */
    public static boolean fermat(BigInteger n) {
        BigInteger a = getRandomBigInteger(n);
        return a.modPow(n.subtract(BigInteger.ONE), n).equals(BigInteger.ONE);
    }
    
    /**
     * Performs a Fermat primality test on the given BigInteger.
     * @param n The BigInteger to test.
     * @param a The BigInteger to use as a test value, given that a < n.
     * @return True if n is a Fermat probable prime using the given number a, or false if not.
     */
    public static boolean fermat(BigInteger n, BigInteger a) {
        return a.modPow(n.subtract(BigInteger.ONE), n).equals(BigInteger.ONE);
    }
    
    /**
     * Gets a new random BigInteger less than the given number.
     * @param n The exclusive maximum.
     * @return The generated BigInteger.
     */
    public static BigInteger getRandomBigInteger(BigInteger n) {
        //This method could probably be optimized. Also, the randomness is compromised by using this method.
        Random rand = new Random();
        BigInteger output = new BigInteger(n.bitLength(), rand).add(new BigInteger("2"));
        while(output.compareTo(n) >= 0) {
            output = output.divide(new BigInteger("10"));
        }
        return output;
    }
    
    /**
     * Performs the Miller-Rabin primality test on the given BigInteger n with k iterations.
     * @param n The BigInteger to perform the test on.
     * @param k The number of times to perform the test. Error bounds is something like 4^-k.
     * @return Whether or not the BigInteger is prime based on the internally generated test values.
     */
    public static boolean millerRabin(BigInteger n, int k) {
        boolean isPrime = true;
        for (int x = 0; x < k; x++) {
            if (!millerRabin(n)) {
                isPrime = false;
            }
        }
        return isPrime;
    }    
    
    private static boolean millerRabin(BigInteger n) {
        BigInteger a = getRandomBigInteger(n);
        int s = 0;
        BigInteger d = n.subtract(BigInteger.ONE);
        //Factor out powers of two (d*(2^s) = n)
        BigInteger[] temp = d.divideAndRemainder(new BigInteger("2"));
        while (temp[1].equals(BigInteger.ZERO)) {
            d = temp[0];
            s++;
            temp = d.divideAndRemainder(new BigInteger("2"));
        }
        int pow = 0;
        do {
            BigInteger res = a.modPow(d.multiply(new BigInteger((int)Math.pow(2, pow) + "")), n);
            //Dunno if multiply by -1
            if (res.equals(n.subtract(BigInteger.ONE)) || res.equals(BigInteger.ONE)) {
                return true;
            }
            pow++;
        } while (pow < s);
        return false;
    }
    
    /**
     * Performs the Solovay-Strassen primality test on the given BigInteger n with k iterations.
     * @param n The BigInteger to perform the test on.
     * @param k The accuracy of the test.
     * @return Whether or not the BigInteger is prime based on the internally generated test value.
     */
    public static boolean solovayStrassen(BigInteger n, int k) {
        if(n.mod(new BigInteger("2")).equals(BigInteger.ZERO)) {
            return false;
        }
        boolean isPrime = true;
        for(int c = 0; c < k; c++) {
            BigInteger a = getRandomBigInteger(n);
            int s = 0;
            BigInteger d = n.subtract(BigInteger.ONE);
            //Factor out powers of two (d*(2^s) = n)
            BigInteger[] temp = d.divideAndRemainder(new BigInteger("2"));
            while (temp[1].equals(BigInteger.ZERO)) {
                d = temp[0];
                s++;
                temp = d.divideAndRemainder(new BigInteger("2"));
            }
            int jacobi = jacobi(a, n);
            BigInteger x = a.modPow(n.subtract(BigInteger.ONE).divide(new BigInteger("2")), n);
            if (!x.equals(new BigInteger(jacobi+"")) && !x.equals(BigInteger.ZERO) ) {
                isPrime = false;
                break;
            }
        }
        return isPrime;
    }
    
    public static int jacobi(BigInteger a, BigInteger b) {
        int signsymbol = 1;
        a = a.mod(b);
        int pow2 = 0;
        while (a.mod(new BigInteger("2")).equals(BigInteger.ZERO)) {
            a = a.divide(new BigInteger("2"));
            pow2++;
        }
        if (pow2 > 0) {
            int nfrom2s = 0;
            int res = b.mod(new BigInteger("8")).intValue();
            switch (res) {
                case 1:
                case 7:
                    nfrom2s = 1;
                    break;
                case 3:
                case 5:
                    nfrom2s = -1;
                    break;
                default:
                    System.err.println("Denominator (n) is even!");
                    return 0;
            }
            //Not sure if this is right. nfrom2s^pow2 might not be right, and idk if you can just mul the two anyway.
            signsymbol *= (int)Math.pow(nfrom2s, pow2);
        }
        if(a.equals(BigInteger.ONE)) {
            return 1*signsymbol;
        }
        if(!a.gcd(b).equals(BigInteger.ONE)) {
            return 0;
        }
        int asignflip = a.mod(new BigInteger("4")).intValue();
        int bsignflip = b.mod(new BigInteger("4")).intValue();
        if(asignflip==3&&bsignflip==3) {
            signsymbol *= -1;
            return jacobi(b, a);
        } else {
            return jacobi(b, a);
        }
        //Execute wikipedia step 4 on jacobi page
    }
    
}