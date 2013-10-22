package graphics;

/**
 * @author Xenith
 */

//The purpose of this class is basically just to create a window containing a buffered frame, if needed
public class Display {
    public static Display display;
    private static BufferedFrame frame;
    
    public Display() {
        display = this;
        frame = new BufferedFrame();
        BufferedFrame.frame = frame;
    }
    
}