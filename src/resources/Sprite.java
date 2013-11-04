package resources;

import java.awt.Image;

/**
 * An extended Image containing coordinates and an identifier.
 * @author Jack
 * @version 1.3 Alpha
 */
public class Sprite {
    
    /**
     * The image to display.
     */
    public Image image;
    /**
     * The x-coordinate of the top-left corner of the destination.
     */
    public int x1;
    /**
     * The x-coordinate of the bottom-right corner of the destination.
     */
    public int x2;
    /**
     * The y-coordinate of the top-left corner of the destination.
     */
    public int y1;
    /**
     * The y-coordinate of the bottom-right corner of the destination.
     */
    public int y2;
    /**
     * The x-coordinate of the top-left corner of the source.
     */
    public int sx1;
    /**
     * The x-coordinate of the bottom-right corner of the source.
     */
    public int sx2;
    /**
     * The y-coordinate of the top-left corner of the source.
     */
    public int sy1;
    /**
     * The y-coordinate of the bottom-right corner of the source.
     */
    public int sy2;
    /**
     * The sprite ID.
     */
    public int id;
    private static int idCounter = 0;
    
    /**
     * Creates a new sprite.
     * @param image The image to display.
     * @param x The x-coordinate of the top-left corner of the destination.
     * @param y The y-coordinate of the bottom-right corner of the destination.
     */
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
    
    /**
     * Creates a new sprite.
     * @param image The image to display.
     * @param x The x-coordinate of the top-left corner of the destination.
     * @param y The y-coordinate of the top-left corner of the destination.
     * @param endx The x-coordinate of the bottom-right corner of the destination.
     * @param endy The y-coordinate of the bottom-right corner of the destination.
     */
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
    
    /**
     * Creates a new sprite.
     * @param image The image to display.
     * @param x The x-coordinate of the top-left corner of the destination.
     * @param y The y-coordinate of the top-left corner of the destination.
     * @param endx The x-coordinate of the bottom-right corner of the destination.
     * @param endy The y-coordinate of the bottom-right corner of the destination.
     * @param srcx1 The x-coordinate of the top-left corner of the source.
     * @param srcy1 The y-coordinate of the top-left corner of the source.
     * @param srcx2 The x-coordinate of the bottom-right corner of the source.
     * @param srcy2 The y-coordinate of the bottom-right corner of the source.
     */
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