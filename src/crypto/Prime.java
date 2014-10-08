package crypto;

import java.util.Arrays;

/**
 * @author Jack
 */
public class Prime {
    
    public void Prime() {
        
    }
    
    public static void main(String[] args) {
        int[] retVal = eratosthenes(1000000);
        System.out.println(retVal);
    }
    
    /**
     * Generates all primes less than a number n using the Sieve of Eratosthenes
     * @param n The maximum value for your primes
     * @return An integer array containing all primes less than the value
     */
    public static int[] eratosthenes(int n) {
        
        int[] possiblePrimes = new int[n-1];
        for(int x = 0; x < n-1; x++){
            possiblePrimes[x] = x + 2;
        }
        for(int x = 0; x < possiblePrimes.length; x++) {
            int mul = possiblePrimes[x]*2;
            while (mul <= possiblePrimes[possiblePrimes.length - 1]) {
                //Remove multiple from array
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
    
}