package crypto;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

/**
 * A collection of methods to encrypt data via variable keylength RSA.
 * @author Jack
 * @version Alpha 1.4
 */
public class RSA {
    
    private BigInteger[] privateKey;
    private BigInteger[] publicKey;

    /**
     * Get the RSA private key as an array containing [n, d].
     * @return The private key as a two-element BigIntegerArray formatted as [n, d].
     */
    public BigInteger[] getPrivateKey() {
        return privateKey;
    }
    
    /**
     * Get the RSA public key as an array containing [n, e].
     * @return The public key as a two-element BigIntegerArray formatted as [n, e].
     */
    public BigInteger[] getPublicKey() {
        return publicKey;
    }
    
    /**
     * Generates a new set of RSA keypairs using the given bitlength.
     * @param bits The bitlength of the keys. For instance, 1024, 2048, 4096, etc.
     * @return A pair of keys in a  multidimensional array formatted as [Public key, Private key] where Public: [n, e] and Private: [n, d].
     */
    public BigInteger[][] generateKeyPairs(int bits) {
        BigInteger p, q, n, phi, e, d;
        p = genPrime(bits);
        q = genPrime(bits);
        n = p.multiply(q);
        phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        Random rnd = new SecureRandom();
        e = genPrime(bits - 1);
        while (!e.gcd(phi).equals(BigInteger.ONE)) {
            e = genPrime(bits - 1);
        }
        d = e.modInverse(phi);
        publicKey = new BigInteger[]{n, e};
        privateKey = new BigInteger[]{n, d};
        return new BigInteger[][] {publicKey, privateKey};
    }
    
    /**
     * Generate a large prime number with the given bitlength.
     * @param bits The bitlength to generate.
     * @return The generated prime as a BigInteger.
     */
    public BigInteger genPrime(int bits) {
        Random rnd = new SecureRandom();
        BigInteger prime = BigInteger.probablePrime(bits,rnd);
        return prime;
    }
    
    /**
     * Encrypt a message using the given public key.
     * @param msg The message to encrypt, formatted as a BigInteger.
     * @param publicKey The public key to use, formatted as [n, e].
     * @return The encrypted message.
     */
    public static BigInteger encrypt(BigInteger msg, BigInteger[] publicKey) {
        return msg.modPow(publicKey[1], publicKey[0]);
    }
    
    /**
     * Decrypt a message using the given private key.
     * @param msg The message to decrypt, formatted as a BigInteger.
     * @param privateKey The private key to use, formatted as [n, d].
     * @return The decrypted message.
     */
    public static BigInteger decrypt(BigInteger msg, BigInteger[] privateKey) {
        return msg.modPow(privateKey[1], privateKey[0]);
    }
    
    /**
     * Decrypt a message using the set private key.
     * @param msg The message to decrypt, formatted as a BigInteger.
     * @return The decrypted message.
     */
    public BigInteger decrypt(BigInteger msg) {
        return msg.modPow(privateKey[1], privateKey[0]);
    }
    
}