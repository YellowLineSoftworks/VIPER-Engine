package graphics;

import game.Clock;
import java.awt.Image;

/**
 * @author Jack
 */
public interface BufferedDevice {

    void render();
    
    int drawImage(Image image, int x, int y);
    
    int drawImage(Image image, int x, int y, int endx, int endy);
    
    int drawImage(Image image, int x, int y, int endx, int endy, int srcx1, int srcy1, int srcx2, int srcy2);
    
    void moveImage(int id, int x, int y);
    
    void moveImage(int id, int x, int y, int endx, int endy);
    
    void moveImage(int id, int x, int y, int endx, int endy, int srx1, int sry1, int srx2, int sry2);
    
    void moveImage(Image image, int x, int y);
    
    void moveImage(Image image, int x, int y, int endx, int endy);
    
    void moveImage(Image image, int x, int y, int endx, int endy, int srx1, int sry1, int srx2, int sry2);
    
    void removeImage(int id);
    
    void removeImage(Image img);
    
    void enableFpsCounter(Clock clock);
    
    void disableFpsCounter();

}