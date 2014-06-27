package graphics.swing;

import game.Clock;
import graphics.BufferedDevice;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferStrategy;
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
 * @version 1.4 Alpha
 */
public class BufferedJFrame extends JFrame implements BufferedDevice {
    
    /**
     * The application's active frame.
     */
    public static BufferedJFrame frame;
    public ResolutionCalculator calc = null;
    protected List<Sprite> sprites = new ArrayList();
    private Mouselistener mouselisten;
    private Keylistener keylisten;
    private BufferStrategy buffer;
    private Graphics graphics;
    private boolean fpscounter = false;
    private boolean switching = false;
    private boolean fullscreen = false;
    private Clock clock;
    private int x = 0;
    private int y = 0;
    
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
        frame.addMouseListener(new DefaultMouselistener());
        frame.addKeyListener(new DefaultKeylistener());
        frame.setTitle(title);
        frame.setSize(width, height);
        frame.setVisible(true);
        frame.setIgnoreRepaint(true);
        frame.createBufferStrategy(2);
        buffer = frame.getBufferStrategy();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
        frame.addMouseListener(mouselisten);
        this.mouselisten = mouselisten;
        frame.addKeyListener(keylisten);
        this.keylisten = keylisten;
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
        sprites.clear();
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
  
}