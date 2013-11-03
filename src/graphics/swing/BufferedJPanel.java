package graphics.swing;

import game.Clock;
import graphics.BufferedDevice;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import resources.Sprite;
import resources.listener.DefaultKeylistener;
import resources.listener.DefaultMouselistener;
import resources.listener.Keylistener;
import resources.listener.Mouselistener;

/**
 * @author Jack
 */
public class BufferedJPanel extends JPanel implements BufferedDevice {

    public static List<BufferedJPanel> canvases = new ArrayList();
    private final List<Sprite> sprites = new ArrayList();
    private Graphics graphics;
    private boolean fpscounter = false;
    private Clock clock;
    
    public BufferedJPanel() {
        
        canvases.add(this);
        this.addMouseListener(new DefaultMouselistener());
        this.addKeyListener(new DefaultKeylistener());
        this.setIgnoreRepaint(true);
        this.setVisible(true);
        
    }
    
    public BufferedJPanel(int width, int height) {
        
        this.setSize(width, height);
        canvases.add(this);
        this.addMouseListener(new DefaultMouselistener());
        this.addKeyListener(new DefaultKeylistener());
        this.setIgnoreRepaint(true);
        this.setVisible(true);
        
    }
    
    public BufferedJPanel(int width, int height, Mouselistener mouselisten, Keylistener keylisten) {
        
        this.setSize(width, height);
        canvases.add(this);
        this.addMouseListener(mouselisten);
        this.addKeyListener(keylisten);
        this.setVisible(true);
        
    }
    
    @Override
    protected void paintComponent(Graphics graphics) {
        graphics.setColor(new Color (255,255,255));
        graphics.fillRect(0, 0, getWidth(), getHeight());
        for (int c = 0; c < sprites.size(); c++) {
            Sprite temp = sprites.get(c);
            graphics.drawImage(temp.image, temp.x1, temp.y1, temp.x2, temp.y2, temp.sx1, temp.sy1, temp.sx2, temp.sy2, null);
	}
        if (fpscounter) {
            graphics.setColor(new Color (0,0,0));
            graphics.drawString(clock.fps + "", 5, 15);
        }
    }
    
    @Override
    public void render(){
        
        this.repaint();
    
    }
    
    @Override
    public int drawImage(Image image, int x, int y){
    
        sprites.add(new Sprite(image, x, y));
        x += 0;
        y += 10;
        return sprites.get(sprites.size() - 1).id;
    
    }
    
    @Override
    public int drawImage(Image image, int x, int y, int endx, int endy) {
        sprites.add(new Sprite(image, x, y, endx, endy));
        x += 0;
        y += 10;
        return sprites.get(sprites.size() - 1).id;
    }
    
    @Override
    public int drawImage(Image image, int x, int y, int endx, int endy, int srcx1, int srcy1, int srcx2, int srcy2) {
        sprites.add(new Sprite(image, x, y, endx, endy, srcx1, srcy1, srcx2, srcy2));
        x += 0;
        y += 10;
        return sprites.get(sprites.size() - 1).id;
    }
    
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
    
    @Override
    public void moveImage(int id, int x, int y, int endx, int endy, int srx1, int sry1, int srx2, int sry2) {
        x += 0;
        y += 10;
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
    
    @Override
    public void moveImage(Image image, int x, int y, int endx, int endy, int srx1, int sry1, int srx2, int sry2) {
        x += 0;
        y += 10;
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