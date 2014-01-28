package server;

import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * A Client is an object that can create and modify Server connections.
 * @author Jack
 * @version 1.4 Alpha
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
            Socket echoSocket = new Socket(hostName, portNumber);
            output = new PrintWriter(echoSocket.getOutputStream(), true);
            input = echoSocket.getInputStream();
        } catch (Exception e) {
            System.err.println("Failed to connect to server: " + hostName + ":" + portNumber);
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
            byte[] buffer = new byte[1024];
            int read;
            String out = "";
            while((read = input.read(buffer)) != -1) {
                out = out + new String(buffer, 0, read);
            }
            return out;
        } catch (Exception e) {
            return "Read error";
        }
    }
    
}