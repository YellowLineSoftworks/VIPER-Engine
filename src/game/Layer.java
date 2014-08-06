package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import resources.GameObject;

/**
 * A Layer is a collection of GameObjects that holds a specific position in the draw order. Similar to layer systems in image editors.
 * They are drawn (or not drawn) based on whether or not the GameObject is set to draw. If an object is added to a Layer, it will stop
 * drawing on the normal interface. Layer visibility can be toggled, but is enabled by default.
 * @author Jack
 * @version 1.4 Alpha
 */
public class Layer {
    
    /**
     * The layer identifier number.
     */
    public int id;
    
    /**
     * A list containing all the created layers.
     */
    public static List<Layer> layers = new ArrayList();
    private List<GameObject> objects = new ArrayList();
    private static int idCounter = 0;
    private boolean visible = true;
    
    /**
     * Creates a new empty layer.
     */
    public Layer() {
        id = idCounter;
        idCounter++;
        layers.add(this);
    }
    
    /**
     * Creates a new layer and initializes it with the given objects.
     * @param objects The objects to add to the layer.
     */
    public Layer(GameObject[] objects) {
        this.objects.addAll(Arrays.asList(objects));
        id = idCounter;
        idCounter++;
        layers.add(this);
    }
    
    /**
     * Add an object to the layer.
     * @param object The object to add.
     * @return The index of the object in the layer.
     */
    public int add(GameObject object) {
        objects.add(object);
        return objects.size() - 1;
    }
    
    /**
     * Add an object to the layer at the given index.
     * @param object The object to add.
     * @return The index of the object in the layer.
     */
    public int add(GameObject object, int index) {
        objects.add(index, object);
        return index;
    }
    
    /**
     * Removes the object at the given ID from the layer.
     * @param id The ID of the object to remove.
     */
    public void remove(int id) {
        for (int i = 0; i < objects.size(); i++) {
            objects.remove(i);
        }
    }
    
    /**
     * Removes the given object from the layer.
     * @param object The object to remove.
     */
    public void remove(GameObject object) {
        for (int i = 0; i < objects.size(); i++) {
            if (objects.get(i).equals(object)) {
                objects.remove(i);
            }
        }
    }
    
    /**
     * Get an array of all the GameObjects in the layer.
     * @return A GameObject[] containing all of the GameObjects in the layer.
     */
    public GameObject[] getObjects() {
        return (GameObject[])objects.toArray();
    }
    
    /**
     * Retrieves the number of objects in the array.
     * @return The number of objects in the array.
     */
    public int size() {
        return objects.size();
    }
    
    /**
     * Sets the layer visibility. Enabled by default.
     * @param visible Whether or not the layer should be visible.
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    
    /**
     * Checks the current layer visibility settings.
     * @return Whether or not the layer is visible.
     */
    public boolean isVisible() {
        return visible;
    }
    
}