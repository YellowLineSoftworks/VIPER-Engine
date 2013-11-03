package graphics.swing;

import game.Clock;
import graphics.BufferedDevice;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import resources.Sprite;
import resources.listener.DefaultKeylistener;
import resources.listener.DefaultMouselistener;
import resources.listener.Keylistener;
import resources.listener.Mouselistener;

/**
 * @author Jack
 */
public class BufferedJFrame extends JFrame implements BufferedDevice {

    public static BufferedJFrame frame;
    private final List<Sprite> sprites = new ArrayList();
    private final BufferStrategy buffer;
    private Graphics graphics;
    private boolean fpscounter = false;
    private Clock clock;
    private int x = 0;
    private int y = 0;
    
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
    }
    
    public BufferedJFrame(int x, int y, int width, int height, String title, Mouselistener mouselisten, Keylistener keylisten) {
        this.x = x;
        this.y = y;
        frame = this;
        frame.setLocation(x, y);
        frame.addMouseListener(mouselisten);
        frame.addKeyListener(keylisten);
        frame.setTitle(title);
        frame.setSize(width, height);
        frame.setVisible(true);
        frame.setIgnoreRepaint(true);
        frame.createBufferStrategy(2);
        buffer = frame.getBufferStrategy();
    }
    
    @Override
    public void render() {
        graphics = buffer.getDrawGraphics();
        graphics.setColor(new Color (255,255,255));
        graphics.fillRect(0, 0, frame.getWidth(), frame.getHeight());
        for (int c = 0; c < sprites.size(); c++) {
            Sprite temp = sprites.get(c);
            graphics.drawImage(temp.image, temp.x1, temp.y1, temp.x2, temp.y2, temp.sx1, temp.sy1, temp.sx2, temp.sy2, null);
	}
        if (fpscounter) {
            graphics.setColor(new Color (0,0,0));
            graphics.drawString(clock.fps + "", 20, 50);
        } 
        graphics.dispose();
        buffer.show();
    }
    
    @Override
    public int drawImage(Image image, int x, int y) {
        sprites.add(new Sprite(image, x, y));
        x += 8;
        y += 30;
        return sprites.get(sprites.size() - 1).id;
    }
    
    @Override
    public int drawImage(Image image, int x, int y, int endx, int endy) {
        sprites.add(new Sprite(image, x, y, endx, endy));
        x += 8;
        y += 30;
        return sprites.get(sprites.size() - 1).id;
    }
    
    @Override
    public int drawImage(Image image, int x, int y, int endx, int endy, int srcx1, int srcy1, int srcx2, int srcy2) {
        sprites.add(new Sprite(image, x, y, endx, endy, srcx1, srcy1, srcx2, srcy2));
        x += 8;
        y += 30;
        return sprites.get(sprites.size() - 1).id;
    }
    
    @Override
    public void moveImage(int id, int x, int y) {
        x += 8;
        y += 30;
        for (Sprite temp: sprites) {
            if (temp.id == id) {
                temp.x1 = x;
                temp.x2 = temp.image.getWidth(null) + x;
                temp.y1 = y;
                temp.y2 = temp.image.getHeight(null) + y;
            }
        }
    }
    
    @Override
    public void moveImage(int id, int x, int y, int endx, int endy) {
        x += 8;
        y += 30;
        for (Sprite temp: sprites) {
            if (temp.id == id) {
                temp.x1 = x;
                temp.x2 = endx;
                temp.y1 = y;
                temp.y2 = endy;
            }
        }
    }
    
    @Override
    public void moveImage(int id, int x, int y, int endx, int endy, int srx1, int sry1, int srx2, int sry2) {
        x += 8;
        y += 30;
        for (Sprite temp: sprites) {
            if (temp.id == id) {
                temp.x1 = x;
                temp.x2 = endx;
                temp.y1 = y;
                temp.y2 = endy;
                temp.sx1 = srx1;
                temp.sy1 = sry1;
                temp.sx2 = srx2;
                temp.sy2 = sry2;
            }
        }
    }
    
    @Override
    public void moveImage(Image image, int x, int y) {
        x += 8;
        y += 30;
        for (Sprite temp: sprites) {
            if (temp.image == image) {
                temp.x1 = x;
                temp.x2 = temp.image.getWidth(null) + x;
                temp.y1 = y;
                temp.y2 = temp.image.getHeight(null) + y;
            }
        }
    }
    
    @Override
    public void moveImage(Image image, int x, int y, int endx, int endy) {
        x += 8;
        y += 30;
        for (Sprite temp: sprites) {
            if (temp.image == image) {
                temp.x1 = x;
                temp.x2 = endx;
                temp.y1 = y;
                temp.y2 = endy;
            }
        }
    }
    
    @Override
    public void moveImage(Image image, int x, int y, int endx, int endy, int srx1, int sry1, int srx2, int sry2) {
        x += 8;
        y += 30;
        for (Sprite temp: sprites) {
            if (temp.image == image) {
                temp.x1 = x;
                temp.x2 = endx;
                temp.y1 = y;
                temp.y2 = endy;
                temp.sx1 = srx1;
                temp.sy1 = sry1;
                temp.sx2 = srx2;
                temp.sy2 = sry2;
            }
        }
    }
    
    @Override
    public void removeImage(int id) {
        for (int c = 0; c < sprites.size(); c++) {
            Sprite temp = sprites.get(c);
            if (temp.id == id) {
                sprites.remove(temp);
            }
        }
    }
    
    @Override
    public void removeImage(Image img) {
        for (Sprite temp: sprites) {
            if (temp.image == img) {
                sprites.remove(temp);
            }
        }
    }
    
    @Override
    public void enableFpsCounter(Clock clock) {
        this.clock = clock;
        fpscounter = true;
    }
    
    @Override
    public void disableFpsCounter() {
        fpscounter = false;
    }

}