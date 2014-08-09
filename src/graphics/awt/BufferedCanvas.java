package graphics.awt;

import game.Clock;
import graphics.BufferedDevice;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Canvas;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;
import resources.ResolutionCalculator;
import resources.Sprite;
import resources.listener.DefaultKeylistener;
import resources.listener.DefaultMouselistener;
import resources.listener.Keylistener;
import resources.listener.Mouselistener;

/**
 * An extension of the Canvas class with built-in double-buffered
 * image drawing and manipulation functions.
 * @author Jack
 * @version 1.4 Alpha
 */
public class BufferedCanvas extends Canvas implements BufferedDevice {
    /**
     * A list of all the application's active canvases.
     */
    public static List<BufferedCanvas> canvases = new ArrayList();
    public ResolutionCalculator calc = null;
    private final List<Sprite> sprites = new ArrayList();
    private KeyListener keylistener = new DefaultKeylistener();
    private MouseListener mouselistener = new DefaultMouselistener();
    private BufferStrategy buffer;
    private Graphics graphics;
    private boolean fpscounter = false;
    private Clock clock;
    private boolean backgroundActive = false;
    
    /**
     * Creates a new BufferedCanvas. Uses the default size and 
     * default mouse and key listeners.
     */
    public BufferedCanvas() {
        
        canvases.add(this);
        this.addMouseListener(mouselistener);
        this.addKeyListener(keylistener);
        this.setIgnoreRepaint(true);
        this.setVisible(true);
        
    }
    
    /**
     * Creates a new BufferedCanvas. Uses default mouse and key 
     * listeners.
     * @param width The width of the canvas.
     * @param height The height of the canvas.
     */
    public BufferedCanvas(int width, int height) {
        
        this.setSize(width, height);
        canvases.add(this);
        this.addMouseListener(mouselistener);
        this.addKeyListener(keylistener);
        this.setIgnoreRepaint(true);
        this.setVisible(true);
        
    }
    
    /**
     * Creates a new BufferedCanvas.
     * @param width The width of the canvas.
     * @param height The height of the canvas.
     * @param mouselisten The canvas's mouse listener.
     * @param keylisten The canvas's key listener.
     */
    public BufferedCanvas(int width, int height, Mouselistener mouselisten, Keylistener keylisten) {
        
        this.setSize(width, height);
        canvases.add(this);
        mouselistener = mouselisten;
        keylistener = keylisten;
        this.addMouseListener(mouselistener);
        this.addKeyListener(keylistener);
        this.setIgnoreRepaint(true);
        this.setVisible(true);
        
    }
    
    /**
     * Renders a frame. Runs every time the game clock advances a tick.
     */
    @Override
    public void render(){
        
        if (buffer == null) {
            this.createBufferStrategy(2);
            buffer = this.getBufferStrategy();
        }
        graphics = buffer.getDrawGraphics();
        graphics.setColor(new Color (255,255,255));
        graphics.fillRect(0, 0, getWidth(), getHeight());
        for (int c = 0; c < sprites.size(); c++) {
            Sprite temp = sprites.get(c);
            if (calc != null) {
                graphics.drawImage(temp.image, calc.calcForX(temp.x1), calc.calcForY(temp.y1), calc.calcForX(temp.x2), calc.calcForY(temp.y2), temp.sx1, temp.sy1, temp.sx2, temp.sy2, null);
            } else {
                graphics.drawImage(temp.image, temp.x1, temp.y1, temp.x2, temp.y2, temp.sx1, temp.sy1, temp.sx2, temp.sy2, null);
            }
	}
        if (fpscounter) {
            graphics.setColor(new Color (0,0,0));
            graphics.drawString(clock.fps + "", 5, 15);
        } 
        graphics.dispose();
        buffer.show();
    
    }
    
    /**
     * Draws an image.
     * @param sprite The sprite you want drawn.
     */
    @Override
    public void drawImage(Sprite sprite){
    
        sprites.add(sprite);
    
    }
    
    /**
     * Draws an image at the given index in the device's sprite[].
     * @param sprite The sprite you want drawn.
     * @param index The index to draw the sprite at.
     */
    @Override
    public void drawImage(Sprite sprite, int index){
        sprites.add(index, sprite);
    }
    
    /**
     * Draws an image.
     * @param image The image you want drawn.
     * @param x The x-value of the image's upper-left corner on the destination frame.
     * @param y The y-value of the image's upper-left corner on the destination frame.
     * @return The generated sprite containing the image identifier and the 
     * display coordinates of the image.
     */
    @Override
    public Sprite drawImage(Image image, int x, int y){
    
        sprites.add(new Sprite(image, x, y));
        x += 0;
        y += 10;
        return sprites.get(sprites.size() - 1);
    
    }
    
    /**
     * Draws an image.
     * @param image The image you want drawn.
     * @param x The x-value of the image's upper-left corner on the destination frame.
     * @param y The y-value of the image's upper-left corner on the destination frame.
     * @param endx The x-value of the image's bottom-right corner on the destination frame.
     * @param endy The y-value of the image's bottom-right corner on the destination frame.
     * @return The generated sprite containing the image identifier and the 
     * display coordinates of the image.
     */
    @Override
    public Sprite drawImage(Image image, int x, int y, int endx, int endy) {
        sprites.add(new Sprite(image, x, y, endx, endy));
        x += 0;
        y += 10;
        return sprites.get(sprites.size() - 1);
    }
    
    /**
     * Draws an image.
     * @param image The image you want drawn.
     * @param x The x-value of the image's upper-left corner on the destination frame.
     * @param y The y-value of the image's upper-left corner on the destination frame.
     * @param endx The x-value of the image's bottom-right corner on the destination frame.
     * @param endy The y-value of the image's bottom-right corner on the destination frame.
     * @param srcx1 The x-value of the upper-left corner of the bounds on the source image.
     * @param srcy1 The y-value of the upper-left corner of the bounds on the source image.
     * @param srcx2 The x-value of the bottom-right corner of the bounds on the source image.
     * @param srcy2 The y-value of the bottom-right corner of the bounds on the source image.
     * @return The generated sprite containing the image identifier and the 
     * display coordinates of the image.
     */
    @Override
    public Sprite drawImage(Image image, int x, int y, int endx, int endy, int srcx1, int srcy1, int srcx2, int srcy2) {
        sprites.add(new Sprite(image, x, y, endx, endy, srcx1, srcy1, srcx2, srcy2));
        x += 0;
        y += 10;
        return sprites.get(sprites.size() - 1);
    }
    
    /**
     * Moves an image.
     * @param id The id of the image to be moved.
     * @param x The x-value of the image's upper-left corner on the destination frame.
     * @param y The y-value of the image's upper-left corner on the destination frame.
     */
    @Override
    public void moveImage(int id, int x, int y) {
        x += 0;
        y += 10;
        for (Sprite temp: sprites) {
            if (temp.id == id) {
                temp.x1 = x;
                temp.x2 = temp.image.getWidth(null) + x;
                temp.y1 = y;
                temp.y2 = temp.image.getHeight(null) + y;
            }
        }
    }
    
    /**
     * Moves an image.
     * @param id The id of the image to be moved.
     * @param x The x-value of the image's upper-left corner on the destination frame.
     * @param y The y-value of the image's upper-left corner on the destination frame.
     * @param endx The x-value of the image's bottom-right corner on the destination frame.
     * @param endy The y-value of the image's bottom-right corner on the destination frame.
     */
    @Override
    public void moveImage(int id, int x, int y, int endx, int endy) {
        x += 0;
        y += 10;
        for (Sprite temp: sprites) {
            if (temp.id == id) {
                temp.x1 = x;
                temp.x2 = endx;
                temp.y1 = y;
                temp.y2 = endy;
            }
        }
    }
    
    /**
     * Moves an image.
     * @param id The id of the image to be moved.
     * @param x The x-value of the image's upper-left corner on the destination frame.
     * @param y The y-value of the image's upper-left corner on the destination frame.
     * @param endx The x-value of the image's bottom-right corner on the destination frame.
     * @param endy The y-value of the image's bottom-right corner on the destination frame.
     * @param srcx1 The x-value of the upper-left corner of the bounds on the source image.
     * @param srcy1 The y-value of the upper-left corner of the bounds on the source image.
     * @param srcx2 The x-value of the bottom-right corner of the bounds on the source image.
     * @param srcy2 The y-value of the bottom-right corner of the bounds on the source image.
     */
    @Override
    public void moveImage(int id, int x, int y, int endx, int endy, int srcx1, int srcy1, int srcx2, int srcy2) {
        x += 0;
        y += 10;
        for (Sprite temp: sprites) {
            if (temp.id == id) {
                temp.x1 = x;
                temp.x2 = endx;
                temp.y1 = y;
                temp.y2 = endy;
                temp.sx1 = srcx1;
                temp.sy1 = srcy1;
                temp.sx2 = srcx2;
                temp.sy2 = srcy2;
            }
        }
    }
    
    /**
     * Moves an image.
     * @param image The image to be moved.
     * @param x The x-value of the image's upper-left corner on the destination frame.
     * @param y The y-value of the image's upper-left corner on the destination frame.
     */
    @Override
    public void moveImage(Image image, int x, int y) {
        x += 0;
        y += 10;
        for (Sprite temp: sprites) {
            if (temp.image == image) {
                temp.x1 = x;
                temp.x2 = temp.image.getWidth(null) + x;
                temp.y1 = y;
                temp.y2 = temp.image.getHeight(null) + y;
            }
        }
    }
    
    /**
     * Moves an image.
     * @param image The image to be moved.
     * @param x The x-value of the image's upper-left corner on the destination frame.
     * @param y The y-value of the image's upper-left corner on the destination frame.
     * @param endx The x-value of the image's lower-right corner on the destination frame.
     * @param endy The y-value of the image's lower-right corner on the destination frame.
     */
    @Override
    public void moveImage(Image image, int x, int y, int endx, int endy) {
        x += 0;
        y += 10;
        for (Sprite temp: sprites) {
            if (temp.image == image) {
                temp.x1 = x;
                temp.x2 = endx;
                temp.y1 = y;
                temp.y2 = endy;
            }
        }
    }
    
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
    @Override
    public void moveImage(Image image, int x, int y, int endx, int endy, int srcx1, int srcy1, int srcx2, int srcy2) {
        x += 0;
        y += 10;
        for (Sprite temp: sprites) {
            if (temp.image == image) {
                temp.x1 = x;
                temp.x2 = endx;
                temp.y1 = y;
                temp.y2 = endy;
                temp.sx1 = srcx1;
                temp.sy1 = srcy1;
                temp.sx2 = srcx2;
                temp.sy2 = srcy2;
            }
        }
    }
    
    /**
     * Removes an image.
     * @param id The id of the image to be removed.
     */
    @Override
    public void removeImage(int id) {
        for (int c = 0; c < sprites.size(); c++) {
            Sprite temp = sprites.get(c);
            if (temp.id == id) {
                sprites.remove(temp);
            }
        }
    }
    
    /**
     * Removes an image.
     * @param img The image to be removed.
     */
    @Override
    public void removeImage(Image img) {
        for (Sprite temp: sprites) {
            if (temp.image == img) {
                sprites.remove(temp);
            }
        }
    }
    
    /**
     * Removes all images.
     */
    @Override
    public void clear() {
        sprites.clear();
    }
    
    /**
     * Enables the device's FPS counter.
     * @param clock The current game clock.
     */
    @Override
    public void enableFpsCounter(Clock clock) {
        this.clock = clock;
        fpscounter = true;
    }
    
    /**
     * Disables the device's FPS counter.
     */
    @Override
    public void disableFpsCounter() {
        fpscounter = false;
    }
    
    /**
     * Tells the canvas to use a ResolutionCalculator to adjust the where things are displayed.
     * @param calc The ResolutionCalculator to use.
     */
    public void useResolutionCalculator(ResolutionCalculator calc) {
        this.calc = calc;
    }
    
    /**
     * Sets the background image.
     * @param image The image to set as the background.
     */
    public void setBackground(Image image) {
        if (backgroundActive) {
            sprites.remove(0);
        }
        sprites.add(0, new Sprite(image, 0, 0));
        backgroundActive = true;
    }
    
    /**
     * Changes the device's mouselistener. If one was not added, replaces the DefaultMouselistener in use with the inputted Mouselistener.
     * @param mouselisten The Mouselistener to use.
     */
    @Override
    public void changeMouseListener(Mouselistener mouselisten) {
        removeMouseListener(mouselistener);
        mouselistener = mouselisten;
        addMouseListener(mouselisten);
    }
    
    /**
     * Changes the device's keylistener. If one was not added, replaces the DefaultKeylistener in use with the inputted Keylistener.
     * @param keylisten The Keylistener to use.
     */
    @Override
    public void changeKeyListener(Keylistener keylisten) {
        removeKeyListener(keylistener);
        keylistener = keylisten;
        addKeyListener(keylisten);
    }
    
}