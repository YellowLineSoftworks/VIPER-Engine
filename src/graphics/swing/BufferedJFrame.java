package graphics.swing;

import game.Clock;
import graphics.BufferedDevice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import resources.ResolutionCalculator;
import resources.Sprite;
import resources.listener.DefaultKeylistener;
import resources.listener.DefaultMouselistener;
import resources.listener.Keylistener;
import resources.listener.Mouselistener;

/**
 * An extension of the JFrame class with built-in double-buffered
 * image drawing and manipulation functions.
 * @author Jack
 * @version 1.5 Alpha
 */
public class BufferedJFrame extends JFrame implements BufferedDevice {
    
    /**
     * The application's active frame.
     */
    public static BufferedJFrame frame;
    public ResolutionCalculator calc = null;
    protected List<Sprite> sprites = new ArrayList();
    private Mouselistener mouselistener = new DefaultMouselistener();
    private Keylistener keylistener = new DefaultKeylistener();
    private BufferStrategy buffer;
    private Graphics graphics;
    private boolean fpscounter = false, enabled3D = false;
    private boolean switching = false;
    private boolean fullscreen = false;
    private Clock clock;
    private int x = 0;
    private int y = 0;
    private boolean backgroundActive = false;
    private Sprite canvas3D;
    
    /**
     * Creates a new BufferedJFrame. Uses default mouse and key listeners.
     * @param x The x-value of the frame's location on the screen.
     * @param y The y-value of the frame's location on the screen.
     * @param width The frame's width.
     * @param height The frame's height.
     * @param title The title of the frame.
     */
    public BufferedJFrame(int x, int y, int width, int height, String title) {
        this.x = x;
        this.y = y;
        frame = this;
        frame.setLocation(x, y);
        frame.addMouseListener(mouselistener);
        frame.addKeyListener(keylistener);
        frame.setSize(width, height);
        frame.setVisible(true);
        frame.setIgnoreRepaint(true);
        frame.createBufferStrategy(2);
        buffer = frame.getBufferStrategy();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    /**
     * Creates a new BufferedJFrame.
     * @param x The x-value of the frame's location on the screen.
     * @param y The y-value of the frame's location on the screen.
     * @param width The frame's width.
     * @param height The frame's height.
     * @param title The title of the frame.
     * @param mouselisten The frame's mouse listener.
     * @param keylisten The frame's key listener.
     * @param undecorated Whether or not the frame should be undecorated.
     */
    public BufferedJFrame(int x, int y, int width, int height, String title, Mouselistener mouselisten, Keylistener keylisten, boolean undecorated) {
        this.x = x;
        this.y = y;
        frame = this;
        frame.setLocation(x, y);
        frame.addMouseListener(this.mouselistener);
        this.mouselistener = mouselisten;
        frame.addKeyListener(this.keylistener);
        this.keylistener = keylisten;
        frame.setTitle(title);
        frame.setSize(width, height);
        frame.setUndecorated(undecorated);
        frame.setVisible(true);
        frame.setIgnoreRepaint(true);
        frame.createBufferStrategy(2);
        buffer = frame.getBufferStrategy();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    /**
     * Renders a frame. Runs every time the game clock advances a tick.
     */
    @Override
    public void render() {
        if (!switching) {
            graphics = buffer.getDrawGraphics();
            graphics.setColor(new Color (255,255,255));
            graphics.fillRect(0, 0, frame.getWidth(), frame.getHeight());
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
                graphics.drawString(clock.fps + "", 20, 50);
            } 
            graphics.dispose();
            buffer.show();
        }
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
    public Sprite drawImage(Image image, int x, int y) {
        sprites.add(new Sprite(image, x, y));
        return sprites.get(sprites.size() - 1);
    }
    
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
    @Override
    public Sprite drawImage(Image image, int x, int y, int endx, int endy) {
        sprites.add(new Sprite(image, x, y, endx, endy));
        return sprites.get(sprites.size() - 1);
    }
    
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
    @Override
    public Sprite drawImage(Image image, int x, int y, int endx, int endy, int srcx1, int srcy1, int srcx2, int srcy2) {
        sprites.add(new Sprite(image, x, y, endx, endy, srcx1, srcy1, srcx2, srcy2));
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
     * @param endx The x-value of the image's lower-right corner on the destination frame.
     * @param endy The y-value of the image's lower-right corner on the destination frame.
     */
    @Override
    public void moveImage(int id, int x, int y, int endx, int endy) {
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
     * @param endx The x-value of the image's lower-right corner on the destination frame.
     * @param endy The y-value of the image's lower-right corner on the destination frame.
     * @param srcx1 The x-value of the upper-left corner of the bounds on the source image.
     * @param srcy1 The y-value of the upper-left corner of the bounds on the source image.
     * @param srcx2 The x-value of the bottom-right corner of the bounds on the source image.
     * @param srcy2 The y-value of the bottom-right corner of the bounds on the source image.
     */
    @Override
    public void moveImage(int id, int x, int y, int endx, int endy, int srcx1, int srcy1, int srcx2, int srcy2) {
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
        if (backgroundActive) {
            Sprite background = sprites.get(0);
            sprites.clear();
            sprites.add(background);
        } else {
            sprites.clear();
        }
    }
    
    /**
     * Centers the frame on the screen.
     */
    public void centerFrame() {
        GraphicsConfiguration config = getGraphicsConfiguration();
        Rectangle screen = config.getBounds();
        frame.setLocation(((screen.width / 2) - (getWidth() / 2)), ((screen.height / 2) - (getHeight() / 2)));
    }
    
    /**
     * Retrieves the screen's dimensions.
     * @return The screen's dimensions.
     */
    public Rectangle getScreenDimensions() {
        return getGraphicsConfiguration().getBounds();
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
     * Enables fullscreen mode.
     * @throws Exception If fullscreen is not supported, throws this exception.
     */
    public void enableFullscreenMode() throws Exception {
        switching = true;
        GraphicsConfiguration config = getGraphicsConfiguration();
        GraphicsDevice myScreen = config.getDevice();
        if (myScreen.isFullScreenSupported()) {
            dispose();
            setUndecorated(true);
            setVisible(true);
            myScreen.setFullScreenWindow(this);  
        } else {
            throw new Exception("GraphicsDevice " + myScreen.getIDstring() + " does not support fullscreen mode.");
        }
        fullscreen = true;
        switching = false;
    }
    
    /**
     * Disables fullscreen mode.
     */
    public void disableFullscreenMode() {
        switching = true;
        GraphicsConfiguration config = getGraphicsConfiguration();
        GraphicsDevice myScreen = config.getDevice();
        dispose();
        setUndecorated(false);
        setVisible(true);
        myScreen.setFullScreenWindow(null); 
        fullscreen = false;
        switching = false;
    }
    
    /**
     * Checks the current screen state.
     * @return True if fullscreen, false if windowed.
     */
    public boolean isFullscreen() {
        return fullscreen;
    }
    
    /**
     * Tells the frame to use a ResolutionCalculator to adjust the where things are displayed.
     * @param calc The ResolutionCalculator to use.
     */
    public void useResolutionCalculator(ResolutionCalculator calc) {
        this.calc = calc;
    }
    
    /**
     * Sets the background image.
     * @param image The image to set as the background.
     */
    @Override
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
    
    /**
     * Gets the device's current mouselistener.
     * @return The device's current mouselistener.
     */
    @Override
    public Mouselistener getMouseListener() {
        return mouselistener;
    }
    
    /**
     * Gets the device's current keylistener.
     * @return The device's current keylistener.
     */
    @Override
    public Keylistener getKeyListener() {
        return keylistener;
    }
    
    /**
     * Gets the size of the Frame.
     * @return The size of the Frame as a Dimension.
     */
    public Dimension getSize() {
        return frame.getBounds().getSize();
    }
    
    /**
     * Gets whether 3D is enabled for the device.
     * @return Whether or not 3D is enabled for the device.
     */
    @Override
    public boolean get3DEnabled() {
        return enabled3D;
    }
    
    /**
     * Sets whether 3D is enabled for the device.
     * @param enabled3D Whether or not to enable 3D for the device.
     */
    @Override
    public void set3DEnabled(boolean enabled3D) {
        if (enabled3D) {
            canvas3D = new Sprite(new BufferedImage(BufferedImage.TYPE_INT_ARGB, getSize().width, getSize().height), 0, 0);
            if (backgroundActive) {
                sprites.add(1, canvas3D);
            } else {
                sprites.add(0, canvas3D);
            }
        } else {
            if (this.enabled3D && backgroundActive) {
                sprites.remove(1);
            } else if (this.enabled3D) {
                sprites.remove(0);
            }
        }
        this.enabled3D = enabled3D;
    }
    
    /**
     * Gets the canvas that the 3D engine is drawing on. Returns null if 3D is not enabled.
     * @return The Sprite that is being used as the 3D canvas.
     */
    public Sprite get3DCanvas() {
        if (get3DEnabled()) {
            return canvas3D;
        } else {
            return null;
        }
    }
    
    
}