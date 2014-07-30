package crypto;

import java.io.File;
import static java.lang.Math.random;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.security.Security;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * A collection of methods to encrypt data via AES-Rijndael.
 * @author Jack
 * @version 1.4 Alpha
 */
public class AES {
    
    /**
     * Securely generate an AES encryption key.
     * @param bits The bitlength of the key to be generated.
     * @return The generated key as a byte[].
     */
    public byte[] genEncryptionKey(int bits) {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(bits);
            SecretKey secretKey = keyGen.generateKey();
            return secretKey.getEncoded();
        } catch (Exception e) {
            System.err.println(e.getStackTrace());
            return null;
        }
    }
    
    /**
     * Encrypts a string of text with the given encryption key.
     * @param plainText The text to encrypt.
     * @param encryptionKey The AES encryption key. Can be generated with {@link #genEncryptionKey(int bits) genEncryptionKey()} or provided.
     * @return The ciphertext as a byte[].
     */
    public static byte[] encrypt(String plainText, byte[] encryptionKey) {
        try {
            SecureRandom random = new SecureRandom();
            byte[] IV = new byte[20];
            random.nextBytes(IV);
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");
            SecretKeySpec key = new SecretKeySpec(encryptionKey, "AES");
            cipher.init(Cipher.ENCRYPT_MODE, key,new IvParameterSpec(IV));
            return cipher.doFinal(plainText.getBytes("UTF-8"));
        } catch (Exception e) {
            System.err.println(e.getStackTrace());
            return new byte[0];
        }
    }
    
    /**Encrypts a file with the given encryption key.
     * @param file The file to encrypt.
     * @param encryptionKey The AES encryption key. Can be generated with {@link #genEncryptionKey(int bits) genEncryptionKey()} or provided.
     * @return The ciphertext as a byte[].
     */
    public static byte[] encrypt(File file, byte[] encryptionKey) {
        try {
            SecureRandom random = new SecureRandom();
            byte[] IV = new byte[20];
            random.nextBytes(IV);
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");
            SecretKeySpec key = new SecretKeySpec(encryptionKey, "AES");
            cipher.init(Cipher.ENCRYPT_MODE, key,new IvParameterSpec(IV));
            Path path = Paths.get(file.getPath());
            byte[] data = Files.readAllBytes(path);
            return cipher.doFinal(data);
        } catch (Exception e) {
            System.err.println(e.getStackTrace());
            return new byte[0];
        }
    }
    
    /**
     * Decrypts ciphertext output by {@link #encrypt(java.lang.String, byte[]) encrypt()} using the given encryption key.
     * @param cipherText The ciphertext to decrypt.
     * @param encryptionKey The AES encryption key. Can be generated with {@link #genEncryptionKey(int bits) genEncryptionKey()} or provided.
     * @return The decrypted ciphertext as a String.
     */
    public static String decrypt(byte[] cipherText, byte[] encryptionKey) {
        try {
            SecureRandom random = new SecureRandom();
            byte[] IV = new byte[20];
            random.nextBytes(IV);
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");
            SecretKeySpec key = new SecretKeySpec(encryptionKey, "AES");
            cipher.init(Cipher.DECRYPT_MODE, key,new IvParameterSpec(IV));
            return new String(cipher.doFinal(cipherText),"UTF-8");
        } catch (Exception e) {
            System.err.println(e.getStackTrace());
            return "";
        }
    }
}