package resources;

import graphics.BufferedDevice;
import java.awt.Graphics;
import java.awt.Image;
import java.util.List;
import java.awt.Point;
import java.awt.Rectangle;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * A parent class for GameObjects.
 * @author Jack
 * @version 1.5 Alpha
 */
public class GameObject {
    
    /**
     * The GameObject identifier.
     */
    public int id;
    /**
     * The location of the top-left corner of the object.
     */
    public Point location;
    /**
     * A list of all the GameObjects in the application.
     */
    public static List<GameObject> objects = new ArrayList();
    /**
     * An array containing the sprites that the object can display.
     */
    public Sprite[] sprites;
    /**
     * The sprite that is currently being displayed.
     */
    public Sprite currentSprite;
    /**
     * A list of the GameObjects that are currently clickable.
     */
    public static List<GameObject> clickableObjects = new ArrayList();
    /**
     * The method to execute on click, if the object is clickable.
     */
    public Method onClick;
    /**
     * Arguments for the method that executes on click, if the object is clickable.
     */
    public Object[] onClickArgs = new Object[]{};
    /**
     * Reference object for the method that executes on click, if the object is clickable.
     */
    public Object onClickReferenceObject = this;
    /**
     * The speed of per-tick movement of the object.
     */
    public double movementSpeed = 0;
    /**
    * The angle of per-tick movement of the object.
    */
    public int movementAngle = 0;
    /**
     * System-used. Do not modify.
     */
    public double movementCounterX = 0;/**
     * System-used. Do not modify.
     */
    public double movementCounterY = 0;
    public boolean bounded = false;
    private int idcounter;
    public final BufferedDevice device;
    private boolean drawn = false;
    private Graphics g = null;
    private static int idCounter = 0;
    
    /**
     * Creates a new GameObject. Remember to call the superconstructor when extending this class.
     * @param device The BufferedDevice which contains the object.
     */
    public GameObject(BufferedDevice device) {
        this.device = device;
        this.location = new Point(0,0);
        this.sprites = new Sprite[0];
        currentSprite = null;
        objects.add(this);
        id = idCounter;
        idCounter++;
        //Currently does not automatically draw the image.
        //currentSprite.id = device.drawImage(currentSprite.image, currentSprite.x1, currentSprite.y1, currentSprite.x2, currentSprite.y2, 
        //        currentSprite.sx1, currentSprite.sy1, currentSprite.sx2, currentSprite.sy2).id;
    }
    
    /**
     * Creates a new GameObject. Remember to call the superconstructor when extending this class.
     * @param sprite The image to use as the source for this object.
     * @param device The BufferedDevice which contains the object.
     */
    public GameObject(Sprite sprite, BufferedDevice device) {
        this.device = device;
        this.location = new Point(sprite.x1, sprite.y1);
        this.sprites = new Sprite[]{sprite};
        currentSprite = sprites[0];
        objects.add(this);
        id = idCounter;
        idCounter++;
    }
    
    /**
     * Creates a new GameObject. Remember to call the superconstructor when extending this class.
     * @param x The x-coordinate of the top-left corner of the object.
     * @param y The y-coordinate of the top-left corner of the object.
     * @param images The images that the object can display.
     * @param device The BufferedDevice which contains the object.
     */    
    public GameObject(int x, int y, Image[] images, BufferedDevice device) {
        this.device = device;
        this.location = new Point(x,y);
        this.sprites = new Sprite[images.length];
        for (int c = 0; c < images.length; c++) {
            sprites[c] = new Sprite(images[c], x, y);
        }
        currentSprite = sprites[0];
        objects.add(this);
        id = idCounter;
        idCounter++;
        //Currently does not automatically draw the image.
        //currentSprite.id = device.drawImage(currentSprite.image, currentSprite.x1, currentSprite.y1, currentSprite.x2, currentSprite.y2, 
        //        currentSprite.sx1, currentSprite.sy1, currentSprite.sx2, currentSprite.sy2).id;
    }
    
    /**
     * Creates a new GameObject. Remember to call the superconstructor when extending this class.
     * @param x The x-coordinate of the top-left corner of the object.
     * @param y The y-coordinate of the top-left corner of the object.
     * @param images The images that the object can display.
     * @param device The BufferedDevice which contains the object.
     */    
    public GameObject(int x, int y, Sprite[] images, BufferedDevice device) {
        this.device = device;
        this.location = new Point(x,y);
        this.sprites = images;
        currentSprite = sprites[0];
        objects.add(this);
        id = idCounter;
        idCounter++;
        //Currently does not automatically draw the image.
        //currentSprite.id = device.drawImage(currentSprite.image, currentSprite.x1, currentSprite.y1, currentSprite.x2, currentSprite.y2, 
        //        currentSprite.sx1, currentSprite.sy1, currentSprite.sx2, currentSprite.sy2).id;
    }
    
    /**                             
     * Creates a new GameObject. Remember to call the superconstructor when extending this class.
     * @param x The x-coordinate of the top-left corner of the object.
     * @param y The y-coordinate of the top-left corner of the object.
     * @param endx The x-coordinate of the bottom-right corner of the object.
     * @param endy The y-coordinate of the bottom-right corner of the object.
     * @param images The images that the object can display.
     * @param device The BufferedDevice which contains the object.
     */    
    public GameObject(int x, int y, int endx, int endy, Image[] images, BufferedDevice device) {
        this.device = device;
        this.location = new Point(x,y);
        this.sprites = new Sprite[images.length];
        for (int c = 0; c < images.length; c++) {
            sprites[c] = new Sprite(images[c], x, y, endx, endy);
        }
        currentSprite = sprites[0];
        objects.add(this);
        id = idCounter;
        idCounter++;
        //Currently does not automatically draw the image.
        //currentSprite.id = device.drawImage(currentSprite.image, currentSprite.x1, currentSprite.y1, currentSprite.x2, currentSprite.y2, 
        //        currentSprite.sx1, currentSprite.sy1, currentSprite.sx2, currentSprite.sy2).id;
    }
    
    /**                             
     * Creates a new GameObject. Remember to call the superconstructor when extending this class.
     * @param x The x-coordinate of the top-left corner of the object.
     * @param y The y-coordinate of the top-left corner of the object.
     * @param endx The x-coordinate of the bottom-right corner of the object.
     * @param endy The y-coordinate of the bottom-right corner of the object.
     * @param images The images that the object can display.
     * @param device The BufferedDevice which contains the object.
     */    
    public GameObject(int x, int y, int endx, int endy, Sprite[] images, BufferedDevice device) {
        this.device = device;
        this.location = new Point(x,y);
        this.sprites = images;
        currentSprite = sprites[0];
        objects.add(this);
        id = idCounter;
        idCounter++;
        //Currently does not automatically draw the image.
        //currentSprite.id = device.drawImage(currentSprite.image, currentSprite.x1, currentSprite.y1, currentSprite.x2, currentSprite.y2, 
        //        currentSprite.sx1, currentSprite.sy1, currentSprite.sx2, currentSprite.sy2).id;
    }
    
    /**
     * Creates a new GameObject. Remember to call the superconstructor when extending this class.
     * @param x The x-coordinate of the top-left corner of the object.
     * @param y The y-coordinate of the top-left corner of the object.
     * @param endx The x-coordinate of the bottom-right corner of the object.
     * @param endy The y-coordinate of the bottom-right corner of the object.
     * @param srcx1 The x-coordinate of the top-left corner of the source image.
     * @param srcy1 The y-coordinate of the top-left corner of the source image.
     * @param srcx2 The x-coordinate of the bottom-right corner of the source image.
     * @param srcy2 The y-coordinate of the bottom-right corner of the source image.
     * @param images The images that the object can display.
     * @param device The BufferedDevice which contains the object.
     */
    public GameObject(int x, int y, int endx, int endy, int srcx1, int srcy1, int srcx2, int srcy2, Image[] images, BufferedDevice device) {
        this.device = device;
        this.location = new Point(x,y);
        this.sprites = new Sprite[images.length];
        for (int c = 0; c < images.length; c++) {
            sprites[c] = new Sprite(images[c], x, y, endx, endy, srcx1, srcy1, srcx2, srcy2);
        }
        currentSprite = sprites[0];
        objects.add(this);
        id = idCounter;
        idCounter++;
        //Currently does not automatically draw the image.
        //currentSprite.id = device.drawImage(currentSprite.image, currentSprite.x1, currentSprite.y1, currentSprite.x2, currentSprite.y2, 
        //        currentSprite.sx1, currentSprite.sy1, currentSprite.sx2, currentSprite.sy2).id;
    }
    
    /**
     * Creates a new GameObject. Remember to call the superconstructor when extending this class.
     * @param x The x-coordinate of the top-left corner of the object.
     * @param y The y-coordinate of the top-left corner of the object.
     * @param endx The x-coordinate of the bottom-right corner of the object.
     * @param endy The y-coordinate of the bottom-right corner of the object.
     * @param srcx1 The x-coordinate of the top-left corner of the source image.
     * @param srcy1 The y-coordinate of the top-left corner of the source image.
     * @param srcx2 The x-coordinate of the bottom-right corner of the source image.
     * @param srcy2 The y-coordinate of the bottom-right corner of the source image.
     * @param images The images that the object can display.
     * @param device The BufferedDevice which contains the object.
     */
    public GameObject(int x, int y, int endx, int endy, int srcx1, int srcy1, int srcx2, int srcy2, Sprite[] images, BufferedDevice device) {
        this.device = device;
        this.location = new Point(x,y);
        this.sprites = images;
        currentSprite = sprites[0];
        objects.add(this);
        id = idCounter;
        idCounter++;
        //Currently does not automatically draw the image.
        //currentSprite.id = device.drawImage(currentSprite.image, currentSprite.x1, currentSprite.y1, currentSprite.x2, currentSprite.y2, 
        //        currentSprite.sx1, currentSprite.sy1, currentSprite.sx2, currentSprite.sy2).id;
    }
    
    /**
     * Changes the currently displayed sprite.
     * @param id The ID of the sprite to change to.
     */
    public void changeDisplayedSprite(int id) {
        if (sprites.length > id) {
            device.removeImage(currentSprite.id);
            currentSprite = sprites[id];
            draw();
        }
    }
    
    /**
     * Changes the available images for the GameObject.
     * @param images An array of the images to change to.
     */
    public void changeSprites(Image[] images) {
        device.removeImage(currentSprite.id);
        Sprite[] newSprites = new Sprite[images.length];
        for (int c = 0; c < images.length; c++) {
            newSprites[c] = new Sprite(images[c], sprites[0].x1, sprites[0].y1, sprites[0].x1 + images[c].getWidth(null), 
                    sprites[0].y1 + images[c].getHeight(null), 0, 0, images[c].getWidth(null), images[c].getHeight(null));
        }
        sprites = newSprites;
        currentSprite = sprites[0];
        draw();
    }
    
    /**
     * Changes the available images for the GameObject.
     * @param x The x-coordinate of the top-left corner of the object.
     * @param y The y-coordinate of the top-left corner of the object.
     * @param endx The x-coordinate of the bottom-right corner of the object.
     * @param endy The y-coordinate of the bottom-right corner of the object.
     * @param srcx1 The x-coordinate of the top-left corner of the source image.
     * @param srcy1 The y-coordinate of the top-left corner of the source image.
     * @param srcx2 The x-coordinate of the bottom-right corner of the source image.
     * @param srcy2 The y-coordinate of the bottom-right corner of the source image.
     * @param images The images that the object can display.
     */
    public void changeSprites(int x, int y, int endx, int endy, int srcx1, int srcy1, int srcx2, int srcy2, Image[] images) {
        device.removeImage(currentSprite.id);
        this.location = new Point(x,y);
        this.sprites = new Sprite[images.length];
        for (int c = 0; c < images.length; c++) {
            sprites[c] = new Sprite(images[c], x, y, endx, endy, srcx1, srcy1, srcx2, srcy2);
        }
        currentSprite = sprites[0];
        draw();
    }
    
    /**
     * Sets the movement of the object.
     * @param speed The speed of movement per tick.
     * @param angle The angle of movement (cartesian plane).
     */
    public void setMovement(double speed, int angle) {
        this.movementSpeed = speed;
        this.movementAngle = angle;
    }
    
    
    /**
     * Moves the object.
     * @param x The x-coordinate of the top-left corner of the destination.
     * @param y The y-coordinate of the bottom-right corner of the destination.
     */
    public void move(int x, int y) {
        if (x < 0 && bounded) {
            x = 0;
        }
        if (y < 0 && bounded) {
            y = 0;
        }
        if (x + currentSprite.image.getWidth(null) > device.getSize().width) {
            x = device.getSize().width - currentSprite.image.getWidth(null);
        }
        if (y + currentSprite.image.getHeight(null) > device.getSize().height) {
            y = device.getSize().height - currentSprite.image.getHeight(null);
        }
        location = new Point(x, y);
        for(int z = 0; z < sprites.length; z++) {
            sprites[z].move(x, y);
        }
        device.moveImage(currentSprite.id, x, y);
    }
    
    /**
     * Moves the object.
     * @param x The x-coordinate of the top-left corner of the destination.
     * @param y The y-coordinate of the bottom-right corner of the destination.
     * @param endx The x-coordinate of the bottom-right corner of the destination.
     * @param endy The y-coordinate of the bottom-right corner of the destination.
     */
    public void move(int x, int y, int endx, int endy) {
        if (x < 0 && bounded) {
            x = 0;
        }
        if (y < 0 && bounded) {
            y = 0;
        }
        if (x > device.getSize().width) {
            x = device.getSize().width;
        }
        if (y > device.getSize().height) {
            y = device.getSize().height;
        }
        location = new Point(x, y);
        for(int z = 0; z < sprites.length; z++) {
            sprites[z].move(x, y, endx, endy);
        }
        device.moveImage(currentSprite.id, x, y, endx, endy);
    }
    
    /**
     * Moves the object.
     * @param x The x-coordinate of the top-left corner of the destination.
     * @param y The y-coordinate of the top-left corner of the destination.
     * @param endx The x-coordinate of the bottom-right corner of the destination.
     * @param endy The y-coordinate of the bottom-right corner of the destination.
     * @param srcx1 The x-coordinate of the top-left corner of the source.
     * @param srcy1 The y-coordinate of the top-left corner of the source.
     * @param srcx2 The x-coordinate of the bottom-right corner of the source.
     * @param srcy2 The y-coordinate of the bottom-right corner of the source.
     */
    public void move(int x, int y, int endx, int endy, int srcx1, int srcy1, int srcx2, int srcy2) {
        if (x < 0 && bounded) {
            x = 0;
        }
        if (y < 0 && bounded) {
            y = 0;
        }
        if (x > device.getSize().width) {
            x = device.getSize().width;
        }
        if (y > device.getSize().height) {
            y = device.getSize().height;
        }
        location = new Point(x, y);
        for(int z = 0; z < sprites.length; z++) {
            sprites[z].move(x, y, endx, endy, srcx1, srcy1, srcx2, srcy2);
        }       
        device.moveImage(currentSprite.id, x, y, endx, endy, srcx1, srcy1, srcx2, srcy2);
    }
    
    /**
     * Draw or redraw the object's current sprite on the frame.
     */
    public void draw() {
        if (currentSprite.image != null) {
            device.removeImage(currentSprite.id);
            currentSprite.id = device.drawImage(currentSprite.image, currentSprite.x1, currentSprite.y1, currentSprite.x2, currentSprite.y2, 
                    currentSprite.sx1, currentSprite.sy1, currentSprite.sx2, currentSprite.sy2).id;
            drawn = true;
        }
    }
    
    /**
     * Draws the object's current sprite on the given Graphics object.
     * @param g The Graphics object to draw the image on.
     */
    public void draw(Graphics g) {
        if (currentSprite.image != null) {
            g.drawImage(currentSprite.image, currentSprite.x1, currentSprite.y1, currentSprite.x2, currentSprite.y2, 
                    currentSprite.sx1, currentSprite.sy1, currentSprite.sx2, currentSprite.sy2, null);
            drawn = true;
            this.g = g;
        }
    }
    
    /**
     * Draws the object's current sprite on the given Graphics object at the given x and y coordinates.
     * @param g The Graphics object to draw the image on.
     * @param x The x-coordinate to draw the image at.
     * @param y The y-coordinate to draw the image at.
     */
    public void draw(Graphics g, int x, int y) {
        if (currentSprite.image != null) {
            g.drawImage(currentSprite.image, x, y, currentSprite.x2 - currentSprite.x1 + x, currentSprite.y2 - currentSprite.y1 + y, 
                    currentSprite.sx1, currentSprite.sy1, currentSprite.sx2, currentSprite.sy2, null);
            drawn = true;
            this.g = g;
        }
    }
    
    /**
     * Adds a method to execute when the object is clicked.
     * @param onClick The method to execute.
     */
    public void addFunctionOnClick(Method onClick) {
        clickableObjects.add(this);
        this.onClick = onClick;
    }
    
    /**
     * Adds a method to execute when the object is clicked.
     * @param onClick The method to execute.
     * @param onClickArgs The arguments for the method.
     * @param onClickReferenceObject The reference object for the method.
     */
    public void addFunctionOnClick(Method onClick, Object[] onClickArgs, Object onClickReferenceObject) {
        clickableObjects.add(this);
        this.onClick = onClick;
        this.onClickArgs = onClickArgs;
        this.onClickReferenceObject = onClickReferenceObject;
    }
    
    /**
     * Destroys the GameObject.
     */
    public void destroy() {
        if(drawn && (g == null || currentSprite != null)) {
            device.removeImage(currentSprite.id);
        }
        sprites = null;
        currentSprite = null;
        objects.remove(this);
        if (clickableObjects.contains(this)) {
            clickableObjects.remove(this);
        }
    }
    
    /**
     * Destroys all GameObjects.
     */
    public static void clear() {
        while(objects.size() > 0) {
            objects.get(0).destroy();
        }
    }
    
    /**
     * Gets the BufferedDevice the object is displaying on.
     * @return The BufferedDevice the object is displaying on.
     */
    public BufferedDevice getDevice() {
        return device;
    }
    
    /**
     * Set whether or not the Object can move outside of the window.
     * @param bounded Whether or not the Object can move outside of the window.
     */
    public void setBounded(boolean bounded) {
        this.bounded = bounded;
    }
    
    /**
     * Returns the bounds of the current sprite.
     * @return The bounds of the current sprite as a Rectangle.
     */
    public Rectangle getObjectBounds() {
        return new Rectangle(location.x, location.y, currentSprite.image.getWidth(null), currentSprite.image.getHeight(null));
    }
    
}