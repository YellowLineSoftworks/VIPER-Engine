package resources;

import graphics.awt.BufferedFrame;
import resources.Sprite;
import java.util.List;
import java.awt.Point;
import java.util.ArrayList;

/**
 * @author Xenith
 */
public class GameObject {
    
    public int id;
    private int idcounter;
    private Point movement;
    public int x;
    public int y;
    public Sprite[] sprites;
    public List<GameObject> objects = new ArrayList();
    public Sprite currentSprite;
    private int spriteID;
    
    public GameObject(int x, int y, Sprite[] sprites) {
        currentSprite = sprites[0];
        this.x = x;
        this.y = y;
        this.sprites = sprites;
        objects.add(this);
        spriteID = BufferedFrame.frame.drawImage(currentSprite.image, currentSprite.x1, currentSprite.y1, currentSprite.x2, currentSprite.y2, 
                currentSprite.sx1, currentSprite.sy1, currentSprite.sx2, currentSprite.sy2);
    }
    
    public void changeSprite(int id) {
        currentSprite = sprites[id];
        draw();
    }
    
    public void setMovement(int x, int y) {
        Point movement = new Point(x, y);
    }
    
    public void draw() {
        BufferedFrame.frame.removeImage(spriteID);
        spriteID = BufferedFrame.frame.drawImage(currentSprite.image, currentSprite.x1, currentSprite.y1, currentSprite.x2, currentSprite.y2, 
                currentSprite.sx1, currentSprite.sy1, currentSprite.sx2, currentSprite.sy2);
    }
    
}