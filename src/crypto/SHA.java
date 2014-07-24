package crypto;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.Formatter;

/**
 * A collection of methods to hash data via SHA.
 * @author Jack
 * @version Alpha 1.4
 */
public class SHA {
    
    /**
     * Hashes the given text using SHA-1.
     * @param text The text to hash.
     * @return The hash as a string.
     */
    public static String sha1(String text) {
        try{
            System.out.println(Security.getProviders());
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            byte[] hash = digest.digest(text.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();
            for (int c = 0; c < hash.length; c++) {
                String hex = Integer.toHexString(0xff & hash[c]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch(Exception e){
            System.err.println(e.getStackTrace());
            return "";
        }
    }
    
    /**
     * Hashes the given file using SHA-1.
     * @param file The file to hash.
     * @return The hash as a string.
     */
    public static String sha1(File file) {
        try {
            final MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            InputStream is = new BufferedInputStream(new FileInputStream(file));
            final byte[] buffer = new byte[1024];
            for (int read = 0; (read = is.read(buffer)) != -1;) {
                messageDigest.update(buffer, 0, read);
            }
            Formatter formatter = new Formatter();
            for (final byte b : messageDigest.digest()) {
                formatter.format("%02x", b);
            }
            return formatter.toString();
        } catch (NoSuchAlgorithmException | IOException e) {
            System.err.println(e.getStackTrace());
            return "";
        }
    }
    
    /**
     * Hashes the given text using SHA-256.
     * @param text The text to hash.
     * @return The hash as a string.
     */
    public static String sha256(String text) {
        try{
            System.out.println(Security.getProviders());
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(text.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();
            for (int c = 0; c < hash.length; c++) {
                String hex = Integer.toHexString(0xff & hash[c]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch(Exception e){
            System.err.println(e.getStackTrace());
            return "";
        }
    }
    
    /**
     * Hashes the given file using SHA-256.
     * @param file The file to hash.
     * @return The hash as a string.
     */
    public static String sha256(File file) {
        try {
            final MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            InputStream is = new BufferedInputStream(new FileInputStream(file));
            final byte[] buffer = new byte[1024];
            for (int read = 0; (read = is.read(buffer)) != -1;) {
                messageDigest.update(buffer, 0, read);
            }
            Formatter formatter = new Formatter();
            for (final byte b : messageDigest.digest()) {
                formatter.format("%02x", b);
            }
            return formatter.toString();
        } catch (NoSuchAlgorithmException | IOException e) {
            System.err.println(e.getStackTrace());
            return "";
        }
    }
    
    /**
     * Hashes the given text using SHA-384.
     * @param text The text to hash.
     * @return The hash as a string.
     */
    public static String sha384(String text) {
        try{
            System.out.println(Security.getProviders());
            MessageDigest digest = MessageDigest.getInstance("SHA-384");
            byte[] hash = digest.digest(text.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();
            for (int c = 0; c < hash.length; c++) {
                String hex = Integer.toHexString(0xff & hash[c]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch(Exception e){
            System.err.println(e.getStackTrace());
            return "";
        }
    }
    
    /**
     * Hashes the given file using SHA-384.
     * @param file The file to hash.
     * @return The hash as a string.
     */
    public static String sha384(File file) {
        try {
            final MessageDigest messageDigest = MessageDigest.getInstance("SHA-384");
            InputStream is = new BufferedInputStream(new FileInputStream(file));
            final byte[] buffer = new byte[1024];
            for (int read = 0; (read = is.read(buffer)) != -1;) {
                messageDigest.update(buffer, 0, read);
            }
            Formatter formatter = new Formatter();
            for (final byte b : messageDigest.digest()) {
                formatter.format("%02x", b);
            }
            return formatter.toString();
        } catch (NoSuchAlgorithmException | IOException e) {
            System.err.println(e.getStackTrace());
            return "";
        }
    }
    
    /**
     * Hashes the given text using SHA-512.
     * @param text The text to hash.
     * @return The hash as a string.
     */
    public static String sha512(String text) {
        try{
            System.out.println(Security.getProviders());
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            byte[] hash = digest.digest(text.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();
            for (int c = 0; c < hash.length; c++) {
                String hex = Integer.toHexString(0xff & hash[c]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch(Exception e){
            System.err.println(e.getStackTrace());
            return "";
        }
    }
    
    /**
     * Hashes the given file using SHA-512.
     * @param file The file to hash.
     * @return The hash as a string.
     */
    public static String sha512(File file) {
        try {
            final MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
            InputStream is = new BufferedInputStream(new FileInputStream(file));
            final byte[] buffer = new byte[1024];
            for (int read = 0; (read = is.read(buffer)) != -1;) {
                messageDigest.update(buffer, 0, read);
            }
            Formatter formatter = new Formatter();
            for (final byte b : messageDigest.digest()) {
                formatter.format("%02x", b);
            }
            return formatter.toString();
        } catch (NoSuchAlgorithmException | IOException e) {
            System.err.println(e.getStackTrace());
            return "";
        }
    }

}