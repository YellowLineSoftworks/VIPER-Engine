package graphics;

import game.Clock;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import resources.Sprite;
import resources.listener.Keylistener;
import resources.listener.Mouselistener;

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
     * Draws an image at the given index in the device's sprite[].
     * @param sprite The sprite you want drawn.
     * @param index The index to draw the sprite at.
     */
    void drawImage(Sprite sprite, int index);
    
    /**
     * Draws an image.
     * @param sprite The sprite you want drawn.
     */
    void drawImage(Sprite sprite);
    
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
    
    /**
     * Add a background image.
     * @param image The image to use as the background.
     */
    void setBackground(Image image);
    
    /**
     * Changes the device's keylistener. If one was not added, replaces the DefaultKeylistener in use with the inputted Keylistener.
     * @param keylisten The Keylistener to use.
     */
    void changeKeyListener(Keylistener keylisten);
    
    /**
     * Changes the device's mouselistener. If one was not added, replaces the DefaultMouselistener in use with the inputted Mouselistener.
     * @param mouselisten The Mouselistener to use.
     */
    void changeMouseListener(Mouselistener mouselisten);
    
    /**
     * Gets the device's current keylistener.
     * @return The device's current keylistener.
     */
    Keylistener getKeyListener();
    
    /**
     * Gets the device's current mouselistener.
     * @return The device's current mouselistener.
     */
    Mouselistener getMouseListener();
    
    Dimension getSize();

}