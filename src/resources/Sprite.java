package resources;

import java.awt.Image;

/**
 * @author YellowLineSoftworks
 */
public class Sprite {
    
    public Image image;
    public int x1;
    public int x2;
    public int y1;
    public int y2;
    public int sx1;
    public int sx2;
    public int sy1;
    public int sy2;
    public int id;
    private static int idCounter = 0;
    
    public Sprite(Image image, int x, int y) {
        x1 = x;
        x2 = image.getWidth(null) + x;
        y1 = y;
        y2 = image.getHeight(null) + y;
        sx1 = 0;
        sy1 = 0;
        sx2 = image.getWidth(null);
        sy2 = image.getHeight(null);
        this.image = image;
        id = idCounter++;
    }
    
    public Sprite(Image image, int x, int y, int endx, int endy) {
        x1 = x;
        x2 = endx;
        y1 = y;
        y2 = endy;
        sx1 = 0;
        sy1 = 0;
        sx2 = image.getWidth(null);
        sy2 = image.getHeight(null);
        this.image = image;
        id = idCounter++;
    }
    
    public Sprite(Image image, int x, int y, int endx, int endy, int srcx1, int srcy1, int srcx2, int srcy2) {
        x1 = x;
        x2 = endx;
        y1 = y;
        y2 = endy;
        sx1 = srcx1;
        sy1 = srcy1;
        sx2 = srcx2;
        sy2 = srcy2;
        this.image = image;
        id = idCounter++;
    }
    
}