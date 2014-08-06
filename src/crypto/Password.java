package crypto;

import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.SecureRandom;
import java.util.Arrays;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * A class that stores passwords securely.
 * @author Jack
 * @version 1.4 Alpha
 */
public class Password {
    
    private byte[] hash = null;
    private byte[] salt = null;
    private int iterations = 0;
    
    /**
     * Create a new password. Hashing is performed automatically. Hash length defaults to 128.
     * @param password The password to hash in string form.
     */
    public Password(String password) {
        SecureRandom random = new SecureRandom();
        salt = new byte[128];
        random.nextBytes(salt);
        iterations = 64000 + random.nextInt(2001);
        hash = PBKDF2(password, new String(salt), iterations, 128);
    }
    
    /**
     * Create a new password. Hashing is performed automatically.
     * @param password The password to hash in string form.
     * @param hashLength The desired hash length.
     */
    public Password(String password, int hashLength) {
        SecureRandom random = new SecureRandom();
        salt = new byte[128];
        random.nextBytes(salt);
        iterations = 64000 + random.nextInt(2001);
        hash = PBKDF2(password, new String(salt), iterations, hashLength);
    }
    
    /**
     * Get the hashed password.
     * @return The hashed password as a String.
     */
    public byte[] getHash() {
        return hash;
    }

    /**
     * Gets the password hash's salt.
     * @return The hashed password as a String.
     */
    public byte[] getSalt() {
        return salt;
    }
    
    /**
     * Get the number of PBKDF2 iterations used to hash the password.
     * @return The number of PBKDF2 iterations used to hash the password.
     */
    public int getIterations() {
        return iterations;
    }
    
    /**
     * Compares a hash with this password's hash.
     * @param bytes The hash to compare.
     * @return Whether or not the hashes (and therefore source passwords) are the same.
     */
    public boolean equals(byte[] bytes) {
        return Arrays.toString(bytes).equals(Arrays.toString(this.hash));
    }
    
    /**
     * Compares a string-formatted password with this one using the given salt.
     * @param pass The password to compare in string form.
     * @return Whether or not the passwords are the same.
     */
    public boolean equals(String pass) {
        return Arrays.toString(PBKDF2(pass, new String(salt), iterations, hash.length)).equals(Arrays.toString(this.hash));
    }
    
    /**
     * Compares another Password with this one.
     * @param pass The password to compare.
     * @return Whether or not the passwords are the same.
     */
    public boolean equals(Password pass) {
        return Arrays.toString(pass.hash).equals(Arrays.toString(this.hash));
    }
    
    /* BEGIN RFC 2898 IMPLEMENTATION */
    private static byte[] PBKDF2(String password, String salt, int iterations, int length) {
        try {
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] P = password.getBytes("UTF-8");
            byte[] S = salt.getBytes("UTF-8");
            double dkLen = length;
            SecretKeySpec keyspec = new SecretKeySpec(P, "HmacSHA256");
            Mac prf = Mac.getInstance("HmacSHA256");
            prf.init(keyspec);
            double hLen = prf.getMacLength();
            if (new BigInteger((int)dkLen + "").compareTo(new BigInteger("2").pow(32).subtract(new BigInteger("-1")).multiply(new BigInteger((int)hLen + ""))) == 1) {
                System.err.println("PBKDF2 derived key too long.");
                return new byte[0];
            } else {
                double l = Math.ceil(dkLen / hLen);
                for (int z = 1; z <= l; z++) {
                    output.write(F(P, S, iterations, z));
                }
            }
            return Arrays.copyOfRange(output.toByteArray(), 0, length);
        } catch (Exception e)  {
            System.err.println("Error in PBKDF2 Function.");
            e.printStackTrace();
            return new byte[0];
        }       
    }
    
    private static byte[] F(byte[] P, byte[] S, int c, int i) throws Exception{
        SecretKeySpec keyspec = new SecretKeySpec(P, "HmacSHA256");
        Mac prf = Mac.getInstance("HmacSHA256");
        prf.init(keyspec);
        byte[] output;
        byte[] intOutput = INT(i);
        byte[] macInput = new byte[S.length + intOutput.length];
        System.arraycopy(S, 0, macInput, 0, S.length);
        System.arraycopy(intOutput, 0, macInput, S.length, intOutput.length);
        byte[] Ul = prf.doFinal(macInput);
        output = Ul;
        prf.reset();
        for (int x = 1; x < c; x++) {
            byte[] temp = prf.doFinal(Ul);
            prf.reset();
            for(int y = 0; y < output.length; y++) {
                output[y] = (byte)(output[y] ^ temp[y]);
            }
            Ul = temp;
        }
        return output;
    }
    
    private static byte[] INT(int i) {
        ByteBuffer buffer = ByteBuffer.allocate(4);
        buffer.order(ByteOrder.BIG_ENDIAN);
        buffer.putInt(i);
        return buffer.array();
    }
    /* END RFC 2898 IMPLEMENTATION */

}