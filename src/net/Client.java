package net;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * A Client is an object that can create and modify Server connections.
 * @author Jack
 * @version 1.5 Alpha
 */
public class Client {
    
    private PrintWriter output = null;
    private InputStream input = null;
    
    /**
     * Creates a new client and attempts to connect.
     * @param hostName The URL or IP of the server.
     * @param portNumber The port to connect to.
     */
    public Client(String hostName, int portNumber) {
        connect(hostName, portNumber);
    }
    
    /**
     * Connects to a server.
     * @param hostName The URL or IP of the server.
     * @param portNumber The port to connect to.
     */
    public void connect(String hostName, int portNumber) {
        try {
            Socket sock = new Socket(hostName, portNumber);
            output = new PrintWriter(sock.getOutputStream(), true);
            input = sock.getInputStream();
        } catch (Exception e) {
            System.err.println("Failed to connect to server: " + hostName + ":" + portNumber);
            e.printStackTrace();
        }
    }
    
    /**
     * Sends data to the server.
     * @param o The data to send.
     */
    public void send(Object o) {
        output.println(o);
        output.flush();
    }
    
    /**
     * Waits for the server to send data and receives it.
     * @return The received data.
     */
    public String receive() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(this.input));
            return in.readLine();
        } catch (Exception e) {
            e.printStackTrace();
            return "Read error";
        }
    }
    
    /**
     * Waits for the server to send data and receives it.
     * @param bytes The number of bytes to receive.
     * @return The received data.
     */
    public String receive(int bytes) {
        try {
            byte[] buffer = new byte[bytes];
            int read;
            String out = "";
            read = input.read(buffer);
            out = new String(buffer, 0, read);
            return out;
        } catch (Exception e) {
            e.printStackTrace();
            return "Read error.";
        }
    }
    
}