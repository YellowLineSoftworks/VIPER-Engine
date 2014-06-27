package graphics;

import game.Clock;
import java.awt.Image;
import resources.Sprite;

/**
 * BufferedDevices are GUI components with built-in image drawing
 * and manipulation functions.
 * @author Jack
 * @version 1.4 Alpha
 */
public interface BufferedDevice {
    
    /**
     * Renders a frame. Runs every time the game clock advances a tick.
     */
    void render();
    
    /**
     * Draws an image.
     * @param image The image you want drawn.
     * @param x The x-value of the image's upper-left corner on the destination frame.
     * @param y The y-value of the image's upper-left corner on the destination frame.
     * @return The generated sprite containing the image identifier and the 
     * display coordinates of the image.
     */
    Sprite drawImage(Image image, int x, int y);
    
    /**
     * Draws an image.
     * @param image The image you want drawn.
     * @param x The x-value of the image's upper-left corner on the destination frame.
     * @param y The y-value of the image's upper-left corner on the destination frame.
     * @param endx The x-value of the image's lower-right corner on the destination frame.
     * @param endy The y-value of the image's lower-right corner on the destination frame.
     * @return The generated sprite containing the image identifier and the 
     * display coordinates of the image.
     */
    Sprite drawImage(Image image, int x, int y, int endx, int endy);
    
    /**
     * Draws an image.
     * @param image The image you want drawn.
     * @param x The x-value of the image's upper-left corner on the destination frame.
     * @param y The y-value of the image's upper-left corner on the destination frame.
     * @param endx The x-value of the image's lower-right corner on the destination frame.
     * @param endy The y-value of the image's lower-right corner on the destination frame.
     * @param srcx1 The x-value of the upper-left corner of the bounds on the source image.
     * @param srcy1 The y-value of the upper-left corner of the bounds on the source image.
     * @param srcx2 The x-value of the bottom-right corner of the bounds on the source image.
     * @param srcy2 The y-value of the bottom-right corner of the bounds on the source image.
     * @return The generated sprite containing the image identifier and the 
     * display coordinates of the image.
     */
    Sprite drawImage(Image image, int x, int y, int endx, int endy, int srcx1, int srcy1, int srcx2, int srcy2);
    
    /**
     * Moves an image.
     * @param id The id of the image to be moved.
     * @param x The x-value of the image's upper-left corner on the destination frame.
     * @param y The y-value of the image's upper-left corner on the destination frame.
     */
    void moveImage(int id, int x, int y);
    
    /**
     * Moves an image.
     * @param id The id of the image to be moved.
     * @param x The x-value of the image's upper-left corner on the destination frame.
     * @param y The y-value of the image's upper-left corner on the destination frame.
     * @param endx The x-value of the image's lower-right corner on the destination frame.
     * @param endy The y-value of the image's lower-right corner on the destination frame.
     */
    void moveImage(int id, int x, int y, int endx, int endy);
    
    /**
     * Moves an image.
     * @param id The id of the image to be moved.
     * @param x The x-value of the image's upper-left corner on the destination frame.
     * @param y The y-value of the image's upper-left corner on the destination frame.
     * @param endx The x-value of the image's lower-right corner on the destination frame.
     * @param endy The y-value of the image's lower-right corner on the destination frame.
     * @param srcx1 The x-value of the upper-left corner of the bounds on the source image.
     * @param srcy1 The y-value of the upper-left corner of the bounds on the source image.
     * @param srcx2 The x-value of the bottom-right corner of the bounds on the source image.
     * @param srcy2 The y-value of the bottom-right corner of the bounds on the source image.
     */
    void moveImage(int id, int x, int y, int endx, int endy, int srcx1, int srcy1, int srcx2, int srcy2);
    
    /**
     * Moves an image.
     * @param image The image to be moved.
     * @param x The x-value of the image's upper-left corner on the destination frame.
     * @param y The y-value of the image's upper-left corner on the destination frame.
     */
    void moveImage(Image image, int x, int y);
    
    /**
     * Moves an image.
     * @param image The image to be moved.
     * @param x The x-value of the image's upper-left corner on the destination frame.
     * @param y The y-value of the image's upper-left corner on the destination frame.
     * @param endx The x-value of the image's lower-right corner on the destination frame.
     * @param endy The y-value of the image's lower-right corner on the destination frame.
     */
    void moveImage(Image image, int x, int y, int endx, int endy);
    
    /**
     * Moves an image.
     * @param image The image to be moved.
     * @param x The x-value of the image's upper-left corner on the destination frame.
     * @param y The y-value of the image's upper-left corner on the destination frame.
     * @param endx The x-value of the image's lower-right corner on the destination frame.
     * @param endy The y-value of the image's lower-right corner on the destination frame.
     * @param srcx1 The x-value of the upper-left corner of the bounds on the source image.
     * @param srcy1 The y-value of the upper-left corner of the bounds on the source image.
     * @param srcx2 The x-value of the bottom-right corner of the bounds on the source image.
     * @param srcy2 The y-value of the bottom-right corner of the bounds on the source image.
     */
    void moveImage(Image image, int x, int y, int endx, int endy, int srcx1, int srcy1, int srcx2, int srcy2);
    
    /**
     * Removes an image.
     * @param id The id of the image to be removed.
     */
    void removeImage(int id);
    
    /**
     * Removes an image.
     * @param img The image to be removed.
     */
    void removeImage(Image img);
    
    /**
     * Removes all images.
     */
    void clear();
    
    /**
     * Enables the device's FPS counter.
     * @param clock The current game clock.
     */
    void enableFpsCounter(Clock clock);
    
    /**
     * Disables the device's FPS counter.
     */
    void disableFpsCounter();

}