package crypto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * A collection of methods to encrypt data via OTP-Blacklight.
 * @author Jack
 * @version 1.5 Alpha
 */
public class OTP {
    
    private int advance = 0;
    private File otp = null;
    
    //todo: add scrambler for the random data
    
    /**
     * Create a new encryption manager using a pregenerated OTP.
     * @param otp The OTP to use.
     */
    public OTP(File otp) {
        this.otp = otp;
    }
    
    /**
     * Create a new encryption manager and generate an OTP using a collection of random data.
     * @param randData The data to use.
     * @param filename The name to use for the OTP file.
     */
    public OTP(String randData, String filename) {
        otp = generateOTP(randData, filename);
    }
    
    /**
     * Create a new encryption manager and generate an OTP using bytes read from a file salted with a collection of random data.
     * @param f The file to use to create the new OTP. Can be anything, but will cause a RAM overload if it's too big.
     * @param randData The data to salt the file with.
     * @param filename The name to use for the OTP file.
     */
    public OTP(File f, String randData, String filename) {
        otp = generateOTP(f, randData, filename);
    }
    
    /**
     * Generate an OTP using bytes read from a file salted with a collection of random data.
     * @param f The file to use to create the new OTP. Can be anything, but will cause a RAM overload if it's too big.
     * @param randData The data to salt the file with.
     * @param filename The name to use for the OTP file.
     * @return The newly created OTP file.
     */
    public File generateOTP(File f, String randData, String filename) {
        try {
            Scanner getFileInput = new Scanner(new BufferedReader(new FileReader(f)));
            String text = "";
            if (getFileInput.hasNext()) {
                text = getFileInput.next();
            }
            String usedDataContainer = "";
            randData = scrubData(randData);
            FileOutputStream fs = new FileOutputStream(new File(filename+".blk"));
            while (!text.equals("")) {
                text = scrubData(text);
                String output = "";
                //todo: output "output" into a file at the end of each of these loops
                if (randData.length() > text.length()) {
                    for(int x = 0; x < text.length(); x++) {
                        char[] textchars = text.toCharArray();
                        char[] data = randData.toCharArray();
                        int outint = -1;
                        if ((int)textchars[x] + (int)data[x] <= 255) {
                            outint = (int)textchars[x] + (int)data[x];
                        } else {
                            outint = (int)textchars[x] + (int)data[x] - 255;
                        }
                        output += (char)outint;
                    }
                    usedDataContainer += randData.substring(0, text.length() - 1);
                    randData = randData.substring(text.length() - 1);
                } else {
                    int preexisting = 0;
                    int strcounter = 0;
                    for(int x = 0; x < text.length(); x++) {
                        if (strcounter + 1 == randData.length() && !usedDataContainer.equals("")) {
                            usedDataContainer += randData;
                            randData = usedDataContainer;
                            usedDataContainer = "";
                            preexisting += strcounter;
                            strcounter = 0;
                        } else if (strcounter + 1 == randData.length() && usedDataContainer.equals("")) {
                            preexisting += strcounter;
                            strcounter = 0;
                        }
                        char[] textchars = text.toCharArray();
                        char[] data = randData.toCharArray();
                        int outint = -1;
                        if ((int)textchars[x] + (int)data[strcounter] <= 255) {
                            outint = (int)textchars[x] + (int)data[strcounter];
                        } else {
                            outint = (int)textchars[x] + (int)data[strcounter] - 255;
                        }
                        output += (char)outint;
                        strcounter++;
                    }
                    usedDataContainer += randData.substring(0, text.length() - 1 - preexisting);
                    randData = randData.substring(text.length() - 1 - preexisting);
                    if (randData.equals("")) {
                        randData = usedDataContainer;
                        usedDataContainer = "";
                    }
                }
                fs.write(output.getBytes());
                if (getFileInput.hasNext()) {
                    text = getFileInput.next();
                } else {
                    text = "";
                }
            }
            fs.close();
        } catch (Exception e) {
            System.out.println("Error generating OTP.");
            e.printStackTrace();
        }
        File file = new File(filename+".blk");
        return file;
    }
    
     /** Generate an OTP using a collection of random data.
     * @param randData The data to use to generate the OTP.
     * @param filename The name to use for the OTP file.
     * @return The newly created OTP file.
     */
    public File generateOTP(String randData, String filename) {
        String scrubbed = scrubData(randData);
        try {
            FileOutputStream fs = new FileOutputStream(filename+".blk");
            fs.write(scrubbed.getBytes());
            fs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        File file = new File(filename+".blk");
        return file;
    }
    
    private static String scrubData(String data) {
        char lastChar = '0';
        for(int x = 0; x < data.length(); x++) {
            char c = data.toCharArray()[x];
            if (c == lastChar) {
                char[] temparray = new char[data.toCharArray().length - 1];
                int tempcounter = 0;
                for (int i = 0; i < data.length(); i++){
                    if (i != x) {
                        temparray[tempcounter] = data.toCharArray()[i];
                        tempcounter++;
                    }
                }
                data = new String(temparray);
                x--;
            } else {
                lastChar = c;
            }
        }
        return data;
    }
    
    /**
     * Encrypt a given string using the set OTP.
     * @param plaintext The text to encrypt.
     * @return The encrypted text as a byte[].
     */
    public byte[] encrypt(String plaintext) {
        try {
            byte[] plain = plaintext.getBytes();
            Path path = Paths.get(otp.getPath());
            byte[] otpData = Files.readAllBytes(path);
            if (!(plain.length > otpData.length)) {
                byte[] encrypted = new byte[plain.length];
                for(int x = 0; x < plain.length; x++) {
                    encrypted[x] = xor(plain[x], otpData[x]);
                }
                advance = plaintext.length();
                return encrypted;
            } else {
                System.err.println("Error: The length of the plaintext is longer than that of the given one time pad. Returning a blank byte[].");
                return new byte[0];
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new byte[0];
        }
    }
    
    /**
     * Decrypt a given byte[] using the set OTP.
     * @param ciphertext The byte[] to decrypt.
     * @return The decrypted text as a string.
     */
    public String decrypt(byte[] ciphertext) {
        try {
            Path path = Paths.get(otp.getPath());
            byte[] otpData = Files.readAllBytes(path);
            if (!(ciphertext.length > otpData.length)) {
                byte[] decrypted = new byte[ciphertext.length];
                for(int x = 0; x < ciphertext.length; x++) {
                    decrypted[x] = xor(ciphertext[x], otpData[x]);
                }
                advance = ciphertext.length;
                return new String(decrypted);
            } else {
                System.err.println("Error: The length of the ciphertext is longer than that of the given one time pad. Returning a blank string.");
                advance();
                return "";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
    
    /**
     * Deletes used parts of the OTP. Run after every usage of encrypt() as soon as you have a transmission receipt from the recipient.
     */
    public void advance() {
        try {
            Path path = Paths.get(otp.getPath());
            byte[] otpData = Files.readAllBytes(path);
            byte[] newOtpData = new byte[otpData.length - advance];
            for(int x = advance; x < otpData.length; x++) {
                newOtpData[x - advance] = otpData[x];
            }
            FileOutputStream fs = new FileOutputStream(otp.getPath());
            otp.delete();
            fs.write(newOtpData);
            fs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static byte xor(byte a, byte b) {
        int ai = (int)a, bi = (int)b;
        int xor = ai ^ bi;
        return (byte)(0xff & xor);
    }
    
}