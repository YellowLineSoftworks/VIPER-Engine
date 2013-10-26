package resources;

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
    public Sprite sprite;
    public List<GameObject> objects = new ArrayList();
    
    public GameObject(int x, int y, Sprite sprite) {
        this.x = x;
        this.y = y;
        this.sprite = sprite;
        objects.add(this);
    }
    
    public void setMovement(int x, int y) {
        Point movement = new Point(x, y);
    }
    
}