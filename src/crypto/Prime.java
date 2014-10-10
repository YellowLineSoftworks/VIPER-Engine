package crypto;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
        Random rnd = new Random();
        do {
            BigInteger i = new BigInteger(n.bitLength(), rnd).add(new BigInteger("2"));
            if (i.compareTo(n) < 0)
                return i;
        } while (true);
    }
    
    /**
     * Performs the Miller-Rabin primality test on the given BigInteger n with k iterations.
     * @param n The BigInteger to perform the test on.
     * @param k Number of Miller-Rabin iterations to perform. Error bounds is something like 4^-k.
     * @return Whether or not the BigInteger is prime based on the internally generated test values.
     */
    public static boolean millerRabin(BigInteger n, int k) {
        if (n.equals(new BigInteger("2"))) {
            return true;
        } else if(n.mod(new BigInteger("2")).equals(BigInteger.ZERO)) {
            return false;
        }
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
     * @param k Number of Solovay-Strassen iterations to perform.
     * @return Whether or not the BigInteger is prime based on the internally generated test value.
     */
    public static boolean solovayStrassen(BigInteger n, int k) {
        if (n.equals(new BigInteger("2"))) {
            return true;
        } else if(n.mod(new BigInteger("2")).equals(BigInteger.ZERO)) {
            return false;
        }
        boolean isPrime = true;
        for(int c = 0; c < k; c++) {
            BigInteger a = getRandomBigInteger(n);
            int jacobi = jacobi(a, n);
            BigInteger jacobiModN = new BigInteger(""+jacobi).mod(n);
            BigInteger x = a.modPow(n.subtract(BigInteger.ONE).divide(new BigInteger("2")), n);
            if (!x.equals(jacobiModN) && !x.equals(BigInteger.ZERO) ) {
                isPrime = false;
                break;
            }
        }
        return isPrime;
    }
    
    /**
     * Calculates the Jacobi symbol for the given a/n.
     * @param a The numerator of the Jacobi calculation.
     * @param n The denominator of the Jacobi calculation.
     * @return The Jacobi symbol for the given a/n
     */
    public static int jacobi(BigInteger a, BigInteger n) {
        int signsymbol = 1;
        if (a.compareTo(BigInteger.ZERO) < 0) {
            signsymbol = -1;
            a = a.negate();
        }
        a = a.mod(n);
        int pow2 = 0;
        while (a.mod(new BigInteger("2")).equals(BigInteger.ZERO)) {
            a = a.divide(new BigInteger("2"));
            pow2++;
        }
        if (pow2 > 0) {
            int nfrom2s = 0;
            int res = n.mod(new BigInteger("8")).intValue();
            if(res == 1 || res == 7) {
                nfrom2s = 1;
            } else if (res == 3 || res == 5) {
                nfrom2s = -1;
            } else {
                System.err.println("Denominator (n) is even!");
                return 0;
            }
            signsymbol *= (int)Math.pow(nfrom2s, pow2);
        }
        if(a.equals(BigInteger.ONE)) {
            return 1*signsymbol;
        }
        if(!a.gcd(n).equals(BigInteger.ONE)) {
            return 0;
        }
        int asignflip = a.mod(new BigInteger("4")).intValue();
        int bsignflip = n.mod(new BigInteger("4")).intValue();
        if(asignflip==3&&bsignflip==3) {
            signsymbol *= -1;
            if (signsymbol < 0) n = n.negate();
            return jacobi(n, a);
        } else {
            if (signsymbol < 0) n = n.negate();
            return jacobi(n, a);
        }
    }
    
    public static boolean lucas(BigInteger n) {
        BigInteger a = getRandomBigInteger(n);
        if (!a.modPow(n.subtract(BigInteger.ONE), n).equals(BigInteger.ONE)) {
            return false;
        }
        int[] qs = primeFactors(n.subtract(BigInteger.ONE));
        for(int q: qs) {
            if (a.modPow(n.subtract(BigInteger.ONE).divide(new BigInteger(""+q)), n).equals(1)) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Get the prime factors of a BigInteger.
     * @param n The number to factor.
     * @return An array of the prime factors of that number.
     */
    public static int[] primeFactors(BigInteger n) {
        List<BigInteger> factors = new ArrayList();
        for (BigInteger i = new BigInteger("2"); i.compareTo(n) <= 0; i = i.add(BigInteger.ONE)) {
          while (n.mod(i).equals(BigInteger.ZERO)) {
            factors.add(i);
            n = n.divide(i);
          }
        }
        int[] factorInts = new int[factors.size()];
        for(int i = 0; i < factors.size(); i++) factorInts[i] = factors.get(i).intValue();
        return factorInts;
    }
    
}